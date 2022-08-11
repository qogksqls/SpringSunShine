<template>
  <div id="webCam">
    <div id="join" v-if="!session">
			<div id="img-div"><img src="resources/images/openvidu_grey_bg_transp_cropped.png" /></div>
			<div id="join-dialog" class="jumbotron vertical-center">
				<h1>Join a video session</h1>
				<div class="form-group">
					<p>
						<label>Participant</label>
						<input v-model="myUserName" class="form-control" type="text" required>
					</p>
					<p>
						<label>Session</label>
						<input v-model="mySessionId" class="form-control" type="text" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
					</p>
				</div>
			</div>
		</div>

    <div class="container" v-if="session">
      <div class="wrap_content row col-md-12 p-4">
        <!--상담사 얼굴 들어갈 자리 start-->
        <div class="col-md-12 counselorFace">
          <sub-video-comp v-if="subscribers.length > 0" :subStreamManager="subscribers[0]"></sub-video-comp>
        </div>
        <!--상담사 얼굴 들어갈 자리 end-->
        <div
          class="col-md-12 row RtcFunction justify-content-center m-0 p-0 align-self-center"
        >
          <div class="col-md-1"></div>

          <!--소리-->
          <div class="iconbtn">
            <i class="fa fa-volume-up fa-2x" aria-hidden="true"></i>
          </div>
          <!--카메라-->

          <div class="iconbtn">
            <i class="fa fa-video-camera fa-2x" aria-hidden="true"></i>
          </div>
          <!--버튼 누르고 닫으면 학생(본인)얼굴 보였다가 안보였다가~-->
          <base-button
            type="primary"
            @click="ShowMe"
            class="col-md-2 align-self-center mx-2"
            >내 화면 보기</base-button
          >
          <!--닫기-->
          <div class="iconbtn2">
            <i
              class="fa fa-times fa-2x"
              aria-hidden="true"
              style="color:#fff"
            ></i>
          </div>
          <!--걍 빈공간 제공한거-->
          <div class="col-md-1"></div>

          <!--학생 얼굴 들어갈 자리 start-->
          <div class="col-md-3 studentFace" v-if="!isFaceShow">
          <main-video-comp :mainStreamManager="mainStreamManager"></main-video-comp></div>
          <!--학생 얼굴 들어갈 자리 end-->
        </div>
      </div>
    </div>
  </div>
</template>
<script>

import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';

import MainVideoComp from './MainVideoComp.vue'
import SubVideoComp from './SubVideoComp.vue'

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://i7a606.q.ssafy.io:8443" ;

const OPENVIDU_SERVER_SECRET = "A606";

export default {

  components: {
		MainVideoComp,
		SubVideoComp,
	},

  data() {
    return {
      isFaceShow: false,

      OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],

			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),

			mute: false,
			closecamera: false,
      active: false,
    };
  },
  methods: {
    ShowMe: function() {
      this.isFaceShow = !this.isFaceShow;
    },

    joinSession () {
			this.OV = new OpenVidu();

			this.session = this.OV.initSession();

			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false,       	// Whether to mirror your local video or not
						});

						this.mainStreamManager = publisher;
						this.publisher = publisher;


						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {

			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},

		hoverToolbar () {
			this.active = true
		},

		leaveToolbar () {
			this.active = false
		},

		muteMySound () {
			this.publisher.publishAudio(false)
			this.mute = !this.mute
		},

		unmuteMySound () {
			this.publisher.publishAudio(true)
			this.mute = !this.mute
		},

		openCamera () {
			this.publisher.publishVideo(true)
			this.closecamera = !this.closecamera
		},

		closeCamera () {
			this.publisher.publishVideo(false)
			this.closecamera = !this.closecamera
		},    
  },

  
};
</script>
<style scoped>
/* html,
body {
  height: 100%;
} */
#webCam {
  background: #fdffbc;
  background: -webkit-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: -moz-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: -o-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: linear-gradient(to right, #fdffbc, #ffeebb, #ffdcb8);
}
button {
  padding: 0;
  height: 2.3rem;
}
.container {
  height: 100vh;
}
.wrap_content {
  height: 100vh;
}
.counselorFace {
  border: 2px solid #fbfbfb;
  height: 80vh;
  background-color: #dcdcdc;
  border-radius: 15px;
}
.studentFace {
  border: 2px solid #fbfbfb;
  background-color: #dcdcdc;
  position: absolute;
  right: 0;
  top: -170px;
  height: 200px;
  border-radius: 15px;
}
.iconbtn {
  text-align: center;
  width: 80px;
  border: 2px solid rgb(255, 255, 255);
  background-color: #bcbcbc;
  border-radius: 50px;
  margin: 0 5px;
}
.iconbtn2 {
  text-align: center;
  width: 80px;
  border: 2px solid rgb(255, 255, 255);
  background-color: brown;
  border-radius: 50px;
  margin: 0 5px;
}
.fa {
  padding: 4px;
  color: rgb(255, 255, 255);
}
</style>

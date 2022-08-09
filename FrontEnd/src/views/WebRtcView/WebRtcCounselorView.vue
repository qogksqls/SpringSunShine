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
        <!--학생 얼굴 들어갈 자리 start-->
        <div class="col-md-6 studentFace mt-5">
          <sub-video-comp v-if="subscribers.length > 0" :subStreamManager="subscribers[0]"></sub-video-comp>
        </div>
        <!--학생 얼굴 들어갈 자리 end-->

        <!--메모장 및 게임 결과 표출-->
        <div class="col-md-6 col mt-5 pr-0">
          <!--메모장 start-->
          <div class="memocard">
            <h3>
              <div class="text-muted text-left mb-2 pr-3 row">
                <b class="col-sm-4">메모장</b>
                <div class="col-sm-6"></div>
                <base-button
                  type="primary"
                  class="savebutton col-sm-2 "
                  @click="addMemo"
                  >저장</base-button
                >
              </div>
            </h3>
            <textarea
              class="col-md-12 memo"
              id="memo"
              v-model="newMemo"
              placeholder="메모하세요."
            ></textarea>
          </div>
          <!--메모장 end-->

          <!--게임표출start-->
          <div class="game">
            <h3>
              <div class="text-muted text-left my-1 pr-3 row">
                <b class="col-sm-4">게임결과</b>
              </div>
            </h3>
            <div class="game_result col-md-12" v-if="!isCardGame">
              클릭시에 나오게 할껀가여 아니면 걍 계속 보이게 할껀가여
            </div>
          </div>
          <!--게임표출end-->
        </div>
        <div
          class="col-md-12 row RtcFunction justify-content-start m-0 p-0 align-self-center"
        >
          <class class="col-md-3"></class>
          <!-- @click="ShowCardGame" -->
          <base-button
            type="success"
            
            class="col-md-1 align-self-center"
            >사물게임</base-button
          ><base-button
            type="success"

            class="col-md-1 align-self-center"
            >피아노게임</base-button
          >
          <!--             @click="ShowMe" -->
          <!--소리-->
          <div class="iconbtn">
            <i class="fa fa-volume-up fa-2x" aria-hidden="true" @click="muteMySound"></i>
          </div>
          <!--카메라-->
          <div class="iconbtn">
            <i class="fa fa-video-camera fa-2x" aria-hidden="true" @click="openCamera"></i>
          </div>
          <!--닫기-->
          <div class="iconbtn2">
            <i
              class="fa fa-times fa-2x"
              aria-hidden="true"
              style="color:#fff"
              @click="leaveSession"
            ></i>
          </div>

          <!--상담사 얼굴 들어갈 자리 start-->
          <div class="col-md-2 counselorFace">
            <main-video-comp :mainStreamManager="mainStreamManager" class="col-md-12"></main-video-comp>
          </div>
          <!--상담사 얼굴 들어갈 자리 end-->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapMutations } from "vuex";
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';

import MainVideoComp from './MainVideoComp.vue'
import SubVideoComp from './SubVideoComp.vue'

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
// const OPENVIDU_SERVER_URL = "https://a606.shop:8443" ;

const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  data() {
    return {
      // videoDiv: document.querySelector('template'),
      // widthOfDiv: null,
      // heightOfDiv: null,

      newMemo: "",
      isCardGame: false,

      OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],

			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),

			mute: false,
			closecamera: false,
    };
  },
  // watch: {
  //   videoDiv () {
  //     this.videoDiv = document.querySelector('template')
  //     this.widthOfDiv = this.videoDiv.offsetWidth
  //     this.heightOfDiv = this.videoDiv.offsetheight
  //     // this.publisher.resolution = `${widthOfDiv}x${heightOfDiv}`
  //     // this.publisher.resolution = `${widthOfDiv}x${heightOfDiv}`
  //   },
  // },
  components: {
		MainVideoComp,
		SubVideoComp,
  },
  methods: {
    ShowCardGame: function() {
      this.isCardGame = !this.isCardGame;
    },
    ...mapMutations(["ADD_MEMO"]),
    addMemo() {
      if (this.newMemo.length == 0) {
        alert("Fill in the blank!");
        return;
      }
      const memo = this.newMemo.replace(/\n|\r\n/g, "<br>");
      this.ADD_MEMO(memo);
      this.newMemo = "";
    },

    // open vidu
    
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
							resolution: '640x480', // The resolution// The resolution of your video
							// resolution: `${widthOfDiv}x${heightOfDiv}`,  // The resolution of your video
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

		muteMySound () {
      this.publisher.publishAudio(this.mute)
      this.mute = !this.mute
		},

		openCamera () {
			this.publisher.publishVideo(this.closecamera)
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
  background: rgb(32, 33, 36);
  /* background: -webkit-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: -moz-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: -o-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: linear-gradient(to right, #fdffbc, #ffeebb, #ffdcb8); */
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
.studentFace {
  border: 2px solid #fbfbfb;
  background-color: #dcdcdc;
  border-radius: 15px;
  height: 64vh;
}
.memo {
  height: 25vh;
  border: 2px solid #fbfbfb;
  background-color: #ffdcb8;
  outline-color: #ffc1b6;
  border-radius: 15px;
  resize: none; /* 사이즈 자유조절 금지*/
}

.game_result {
  height: 25vh;
  border: 2px solid #fbfbfb;
  background-color: #ffdcb8;
  outline-color: #ffc1b6;
  border-radius: 15px;
}
/* 스크롤 관련,,, */
textarea::-webkit-scrollbar {
  width: 12px;
  background-color: #f5f5f500;
}
textarea::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
  background-color: #ffc1b6;
}
/* 스크롤 관련,,,ㅇ */
.counselorFace {
  border: 2px solid #fbfbfb;
  background-color: #dcdcdc;
  position: absolute;
  right: 0;
  top: -85px;
  height: 140px;
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

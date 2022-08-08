<template>
	<div id="main-container" class="container">
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

		<div id="session" v-if="session">
			<div id="session-header">
				<h1 id="session-title">{{ mySessionId }}</h1>
			</div>
			
			<main-video-comp :mainStreamManager="mainStreamManager"></main-video-comp>
			<sub-video-comp v-if="subscribers.length > 0" :subStreamManager="subscribers[0]"></sub-video-comp>

			<div id="tool-bar" @mouseenter="hoverToolbar" @mouseleave="leaveToolbar">
				<div v-if="this.active" id="tools">

					<img v-if="!this.mute" id="mute_img" src="../assets/mute.png" @click="muteMySound">
					<img v-else id="unmute_img" src="../assets/unmute.png" @click="unmuteMySound">

					<img id="leaveSession_img" src="../assets/stop_call.png" @click="leaveSession">

					<img v-if="this.closecamera" id="closecamera_img" src="../assets/opencamera.png" @click="openCamera">
					<img v-else id="opencamera_img" src="../assets/closecamera.png" @click="closeCamera">
					
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

// const OPENVIDU_SERVER_URL = "https://13.125.192.199:4443/";
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
	name: 'App',

	components: {
		MainVideoComp,
		SubVideoComp,
	},

	data () {
		return {
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
		}
	},

	methods: {
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();

			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---

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

						// --- Publish your stream ---

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
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

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
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

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
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

		// sendMassage () {

		// 	this.session.signal({
		// 		data: this.chatText,  // Any string (optional)
		// 		})
		// 		.then(() => {
		// 			console.log('Message successfully sent');
		// 		})
		// 		.catch(error => {
		// 			console.error(error);
		// 		});

		// 	this.chat.push(this.chatText)
		// 	this.chatText = null

		// 	this.session.on('signal', (event) => {
		// 		console.log(event.data); // Message
		// 		console.log(event.from); // Connection object of the sender
		// 		console.log(event.type); // The type of message
		// 	});
		// }
	}
}
</script>
<style scoped>
	.wrap_cont{
		position: relative;
		width: 55%;
	}

	.sub_vid{
		position: absolute;
		right: -45%;
		bottom: 0;
		width: 40%;
	}

		#tool-bar{
		position: absolute;
		bottom: 0;
		left: 0;

		height: 20%;
		width: 100%;
	}

	#tools{
		display: flex;
		justify-content: space-around;
		background-color: rgba(255, 255, 255, 0.5);
		height: 50%;
	}
	
	#tools>img{
		width: 8%;
	}

	#chat-div{
		position: absolute;
		left: 65%;
		bottom: 20%;
		background-color: bisque;

		width: 30%;
		height: 70%;
	}

	#chat_input{
		position: absolute;
		display: flex;
		bottom: 0;
		width: 100%;
	}

	#chat_input>input{
		width: 95%;
	}

	#chat_input>img{
		width: 5%;
	}
</style>
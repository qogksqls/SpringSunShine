<template>
  <div id="webCam">
    <div id="join" v-if="!session">
      <p class="text-center">
        <button class="btn btn-lg btn-success" @click="joinSession()">
          Join!
        </button>
      </p>
    </div>

    <div class="container" v-if="session">
      <div class="wrap_content row col-md-12 p-4 m-0 justify-content-around">
        <!--상담사 얼굴 들어갈 자리 start-->
        <div class="col-md-8 counselorFace p-0" v-if="!playingNow">
          <sub-video-comp
            :key="subscribers[0].stream.connection.connectionId"
            v-if="subscribers.length > 0"
            :subStreamManager="subscribers[0]"
          ></sub-video-comp>
        </div>
        <!--상담사 얼굴 들어갈 자리 end-->

        <!--학생 얼굴 들어갈 자리 start-->
        <div class="col-md-3 studentFace align-self-end p-0" v-if="isFaceShow">
          <main-video-comp
            :mainStreamManager="mainStreamManager"
          ></main-video-comp>
        </div>
        <!--학생 얼굴 들어갈 자리 end-->

        <div id="cardGameDiv" v-if="playingNow">
          <cards-comp></cards-comp>
        </div>

        <div
          class="col-md-12 row RtcFunction justify-content-center mt-3 p-0 align-self-center"
        >
          <div class="col-md-1"></div>
          <!--카드게임-->
          <base-button
            @click="shareScreen"
            class="col-md-2 align-self-center mx-2"
            type="success"
            >카드게임</base-button
          >

          <!--버튼 누르고 닫으면 학생(본인)얼굴 보였다가 안보였다가~-->
          <base-button
            type="primary"
            @click="ShowMe"
            class="col-md-2 align-self-center mx-2"
            >내 화면 보기</base-button
          >
          <!--소리-->
          <base-button type="danger" class="iconbtn">
            <i
              class="fa fa-volume-up fa-2x"
              aria-hidden="true"
              @click="muteMySound"
            ></i>
          </base-button>
          <!--카메라-->
          <base-button type="danger" class="iconbtn">
            <i
              class="fa fa-video-camera fa-2x"
              aria-hidden="true"
              @click="openCamera"
            ></i>
          </base-button>

          <!--닫기-->
          <base-button type="danger" class="iconbtn2">
            <i
              class="fa fa-times fa-2x"
              aria-hidden="true"
              style="color:#fff"
              @click="leaveSession"
            ></i>
          </base-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";

import MainVideoComp from "./MainVideoComp.vue";
import SubVideoComp from "./SubVideoComp.vue";

import CardsComp from "@/components/webRtcComp/CardsComp.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

// const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_URL = "https://i7a606.q.ssafy.io:8443";

// const OPENVIDU_SERVER_SECRET = "MY_SECRET";
const OPENVIDU_SERVER_SECRET = "A606";

export default {
  props: {
    child_id: String,
  },
  components: {
    MainVideoComp,
    SubVideoComp,
    CardsComp,
  },
  computed: {
    changePlayingNow() {
      let playingNow = this.playingNow;
      if (this.$store.state.cardGame.playingNow !== playingNow) {
        playingNow = this.$store.state.cardGame.playingNow;
      }
      return playingNow;
    },

    changeEndGame() {
      let endGame = this.endGame;
      if (this.$store.state.cardGame.endGame !== endGame) {
        endGame = this.$store.state.cardGame.endGame;
      }
      return endGame;
    },
  },
  watch: {
    changePlayingNow(val) {
      this.playingNow = val;
    },
    changeEndGame(val) {
      if (!val) return;
      this.shareScreen();
      this.$store.state.cardGame.endGame = false;
    },
  },
  data() {
    return {
      isFaceShow: true,

      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      sessionScreen: undefined,

      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),

      mute: false,
      closecamera: false,

      sharedScreen: false,

      playingNow: false,
      endGame: false,
    };
  },
  methods: {
    shareScreen() {
      if (this.sharedScreen) {
        this.$store.state.cardGame.playingNow = false;
        this.session.unpublish(this.sessionScreen);
        this.session.publish(this.publisher);
      } else {
        this.$store.state.cardGame.playingNow = true;

        this.isFaceShow = false;
        let publisher = this.OV.initPublisher("html-element-id", {
          audioSource: undefined, // The source of audio. If undefined default microphone // The source of video. If undefined default webcam
          publishAudio: !this.mute, // Whether you want to start publishing with your audio unmuted or not
          publishVideo: !this.closecamera, // Whether you want to start publishing with your video enabled or not
          resolution: "640x480", // The resolution of your video
          frameRate: 30, // The frame rate of your video
          insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
          mirror: false, // Whether to mirror your local video or not
          videoSource: "screen",
          //openCard: false,
        });
        this.sessionScreen = publisher;

        this.sessionScreen.once("accessAllowed", (event) => {
          this.sessionScreen.stream
            .getMediaStream()
            .getVideoTracks()[0]
            .addEventListener("ended", () => {
              console.log('User pressed the "Stop sharing" button');
            });
        });

        publisher.once("accessDenied", (event) => {
          console.warn("ScreenShare: Access Denied");
        });
        this.session.unpublish(this.publisher);
        this.session.publish(this.sessionScreen);
      }
      this.sharedScreen = !this.sharedScreen;
    },
    ShowMe: function() {
      this.isFaceShow = !this.isFaceShow;
    },
    joinSession() {
      //this.mySessionId = "Session_" + this.child_id;
      this.mySessionId = "Session_" + "A";
      //this.myUserName = this.child_id;
      this.myUserName = "B";

      console.log("----------------");
      console.log(this.mySessionId);
      console.log(this.myUserName);
      console.log("----------------");

      this.OV = new OpenVidu();

      this.session = this.OV.initSession();
      //this.sessionScreen = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });
      console.log("--------------------");
      console.log(this.subscribers);
      console.log("--------------------");

      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });
            console.log("--------------------");
            console.log(publisher);
            console.log("--------------------");
            this.mainStreamManager = publisher;
            this.publisher = publisher;

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
      this.$router.push("/");
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },

    muteMySound() {
      if (this.sharedScreen) {
        this.sessionScreen.publishAudio(this.mute);
        this.mute = !this.mute;
      } else {
        this.publisher.publishAudio(this.mute);
        this.mute = !this.mute;
      }
    },

    openCamera() {
      if (this.sharedScreen) {
        this.sessionScreen.publishVideo(this.closecamera);
        this.closecamera = !this.closecamera;
      } else {
        this.publisher.publishVideo(this.closecamera);
        this.closecamera = !this.closecamera;
      }
    },
  },
};
</script>
<style scoped>
/* html,
body {
  height: 100%;
} */
.wrap_content {
  display: flex;
  align-items: center;
}
#webCam {
  background-size: cover;
  background: -webkit-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: -moz-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: -o-linear-gradient(right, #fdffbc, #ffeebb, #ffdcb8);
  background: linear-gradient(to right, #fdffbc, #ffeebb, #ffdcb8);
  width: 100%;
  height: 100%;
}
.container {
  height: 100%;
}
button {
  padding: 0;
  height: 2.3rem;
  margin: 5px 0;
}
.studentFace {
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
  border-radius: 50px;
  margin: 0 5px;
}
.fa {
  padding: 4px;
  color: rgb(255, 255, 255);
}

#cardGameDiv {
  width: 100%;
  height: 80%;
  bottom: 40%;
}

#cardGameInnerDiv {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #dcdcdc;
}

@media (min-width: 768px) {
  .container {
    height: 100%;
  }
  #webCam {
    padding: 60px 0;
  }
}
@media (min-width: 1200px) and (min-height: 750px) {
  #webCam {
    height: 100vh;
    padding: 50px 0;
  }
}
</style>

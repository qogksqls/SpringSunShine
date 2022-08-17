<template>
  <div id="webCam">
    <!-- 
    <div id="join" v-if="!session">

					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
					</p>

		</div> -->

    <div class="container" v-if="session">
      <div class="wrap_content row">
        <!--학생 얼굴 들어갈 자리 start-->
        <div class="col-md-6 studentFace mt-5">
          <sub-video-comp
            class="sub_vid"
            :key="subscribers[0].stream.connection.connectionId"
            v-if="subscribers.length > 0"
            :subStreamManager="subscribers[0]"
          ></sub-video-comp>
          <!-- <screen-share-comp v-if="sessionScreen" :sessionScreen="sessionScreen"></screen-share-comp> -->
        </div>
        <!--학생 얼굴 들어갈 자리 end-->

        <!--상담사 얼굴 들어갈 자리 start-->
        <div class="counselorFace col-md-6 mt-5">
          <!-- <main-video-comp :widthOfVideo="widthOfVideo" :heightOfVideo="heightOfVideo" :mainStreamManager="mainStreamManager" class="col-md-12"></main-video-comp> -->
          <main-video-comp
            :mainStreamManager="mainStreamManager"
          ></main-video-comp>
        </div>
        <!--상담사 얼굴 들어갈 자리 end-->

        <!--메모장 및 게임 결과 표출-->
        <div class="col-md-12 my-md-3">
          <!--메모장 start-->
          <div class="memocard">
            <h4>
              <div class="text-muted text-left m-0 row justify-content-between">
                <b class="col-5 px-0 py-1">메모장</b>
                <base-button
                  type="primary"
                  class="savebutton col-2 "
                  @click="addMemo"
                  >저장</base-button
                >
              </div>
            </h4>
            <textarea
              class="col-md-12 memo"
              id="memo"
              v-model="newMemo"
              placeholder="메모하세요."
            ></textarea>
          </div>
          <!--메모장 end-->
        </div>

        <div
          class="col-md-12 row RtcFunction justify-content-center m-0 p-0 align-items-center"
        >
          <base-button
            data-toggle="modal"
            data-target="#exampleModal"
            type="success"
            @click="ShowCardGame"
            class="col-sm-2 align-self-center"
            >카드게임</base-button
          >

          <!-- <base-button type="success" class="col-md-1 align-self-center"
            >피아노게임</base-button
          > -->

          <!--             @click="ShowMe" -->
          <!--소리-->
          <base-button type="danger" class="iconbtn col-sm-2">
            <i
              class="fa fa-volume-up fa-2x"
              aria-hidden="true"
              @click="muteMySound"
            ></i>
          </base-button>
          <!--카메라-->
          <base-button type="danger" class="iconbtn col-sm-2">
            <i
              class="fa fa-video-camera fa-2x"
              aria-hidden="true"
              @click="openCamera"
            ></i>
          </base-button>
          <!--닫기-->
          <base-button type="danger" class="iconbtn2 col-sm-2">
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
import { mapMutations } from "vuex";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";

import MainVideoComp from "./MainVideoComp.vue";
import SubVideoComp from "./SubVideoComp.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

// const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_URL = "https://i7a606.q.ssafy.io:8443";

// const OPENVIDU_SERVER_SECRET = "MY_SECRET";
const OPENVIDU_SERVER_SECRET = "A606";

export default {
  name: "CounselorView",
  mounted() {
    this.joinSession();
  },
  props: {
    ids: Object,
  },
  // watch: {
  //   widthOfVideo () {
  //     if (this.widthOfVideo) {
  //       widthOfVideo = window.outerWidth
  //     }
  //     console.log(this.widthOfVideo)
  //   },
  //   heightOfVideo () {
  //     if (this.heightOfVideo) {
  //       heightOfVideo = window.outerHeight
  //     }
  //     console.log(this.heightOfVideo)
  //   }
  // },
  data() {
    return {
      // widthOfVideo: window.outerWidth,
      // heightOfVideo: window.outerHeight,

      newMemo: "",
      isCardGame: false,

      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      // sessionScreen: undefined,

      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),

      mute: false,
      closecamera: false,

      consultNo: null,
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
      this.publisher.openCard = !this.publisher.openCard;
      console.log(this.publisher.openCard);
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
    getRecordCount() {
      axios
        .get(
          `https://i7a606.q.ssafy.io/service-api/consult/therapistcount/${
            this.ids.thera_Id
          }/${this.ids.child_Id}`
        )
        .then((res) => {
          return res.data;
        });
    },

    joinSession() {
      /*       axios
        .post("https://i7a606.q.ssafy.io/service-api/consult/room", {
          theraId: this.ids.thera_Id,
          childId: this.ids.child_Id,
          parentId: this.ids.parent_Id,
        })
        .then((res) => {
          this.consultNo = res.data.consultNo;
        }); */

      //this.mySessionId = "Session_" + this.ids.child_id;
      this.mySessionId = "Session_" + "A";

      //this.myUserName = this.ids.thera_id;
      this.myUserName = "A";

      this.OV = new OpenVidu();

      this.session = this.OV.initSession();
      // this.sessionScreen = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

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

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            this.session.publish(this.publisher);
          })
          // .then(() => {
          //   let publisher = this.OV.initPublisher("html-element-id", {
          //     videoSource: "screen"
          //   });
          //   this.sessionScreen = publisher

          //   this.sessionScreen.once('accessAllowed', (event) => {
          //       this.sessionScreen.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
          //           console.log('User pressed the "Stop sharing" button');
          //         });
          //         this.session.publish(this.sessionScreen);

          //     });

          //   publisher.once('accessDenied', (event) => {
          //       console.warn('ScreenShare: Access Denied');
          //   });

          // })
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
      axios.put("https://i7a606.q.ssafy.io/service-api/consult/memo", {
        consultNo: this.consultNo,
        memo: this.$store.state.memos.list.toString(),
      });
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
      this.$router.push({
        name: "childReserveShowCounselor",
        params: {
          reservTime: datetime,
          childId: String,
          childName: String,
          parentName: String,
        },
      });
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
      this.publisher.publishAudio(this.mute);
      this.mute = !this.mute;
    },

    openCamera() {
      this.publisher.publishVideo(this.closecamera);
      this.closecamera = !this.closecamera;
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
}
button {
  padding: 0;
  height: 2.3rem;
  margin: 5px 0;
}
.studentFace {
  border-radius: 15px;
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
.g_result {
  padding-bottom: 5px;
}
.sub_vid {
}
@media (min-width: 768px) and (min-height: 750px) and (max-width: 1200px) {
  .container {
    height: 100%;
  }
  #webCam {
    padding: 60px 0;
  }
}
@media (min-width: 1200px) and (min-height: 900px) {
  .container {
    height: 100vh;
  }
  #webCam {
    padding: 20px 0;
  }
}
@media (min-width: 990px) and (max-width: 1200px) {
  .container {
    height: 100vh;
  }
}
</style>

<template>
  <div id="webCam">
    <div class="container">
      <div class="wrap_content row col-md-12 p-4">
        <!--학생 얼굴 들어갈 자리 start-->
        <div class="col-md-6 studentFace mt-5">학생 얼굴이요~</div>
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
          <base-button
            type="success"
            @click="ShowCardGame"
            class="col-md-1 align-self-center"
            >사물게임</base-button
          ><base-button
            type="success"
            @click="ShowMe"
            class="col-md-1 align-self-center"
            >피아노게임</base-button
          >
          <!--소리-->
          <div class="iconbtn">
            <i class="fa fa-volume-up fa-2x" aria-hidden="true"></i>
          </div>
          <!--카메라-->
          <div class="iconbtn">
            <i class="fa fa-video-camera fa-2x" aria-hidden="true"></i>
          </div>
          <!--닫기-->
          <div class="iconbtn2">
            <i
              class="fa fa-times fa-2x"
              aria-hidden="true"
              style="color:#fff"
            ></i>
          </div>

          <!--상담사 얼굴 들어갈 자리 start-->
          <div class="col-md-2 counselorFace">상담사 얼굴이요~</div>
          <!--상담사 얼굴 들어갈 자리 end-->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      newMemo: "",
      isCardGame: false,
    };
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

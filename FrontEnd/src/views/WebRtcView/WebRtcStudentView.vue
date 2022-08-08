<template>
  <div id="webCam">
    <div class="container">
      <div class="wrap_content row col-md-12 p-4">
        <!--상담사 얼굴 들어갈 자리 start-->
        <div class="col-md-12 counselorFace"></div>
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
          <div class="col-md-3 studentFace" v-if="!isFaceShow"></div>
          <!--학생 얼굴 들어갈 자리 end-->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isFaceShow: false,
    };
  },
  methods: {
    ShowMe: function() {
      this.isFaceShow = !this.isFaceShow;
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

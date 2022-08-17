<template
  ><div>
    <section class="section-hero section-shaped my-0">
      <!-- <img src="img/theme/mainbg.jpg" class="bg" alt="" /> -->
      <div class="shape shape-style-1 shape-primary"></div>
      <div class="container">
        <div id="title" class="align-items-center">
          <!--  <p id="titleMsg" class="mt-md-5">
              <b>☀봄날의햇살☀</b>
          </p> -->
          <div id="titleImg" class="col-md-4 titleImg">
            <img src="/img/main/cloud.png" alt="봄날의햇살" />
            <p id="sun" class="col-md-5">
              <img src="/img/main/sun.png" alt="" />
            </p>
            <p id="titleSub" class="text-left mt-lg-3">
              <span
                >☀ 아이와 함께 걸어가는 길 <br />봄날의 햇살이
                비추겠습니다.</span
              >
            </p>
            <div class="btn col align-self-center p-0 mt-3">
              <router-link to="/counselorRecommend"
                ><base-button block>상담받기</base-button></router-link
              >
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
export default {};
</script>
<style scoped>
.section-hero {
  height: 100vh;
  width: 100vw;
  background-image: url("../../../public/img/theme/mainbg_1024.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  width: 100%;
  position: absolute;
  background-size: cover;
}
#titleSub {
  font-weight: 500;
  color: #989898;
  font-size: 1.3rem;
}
#titleMsg {
  color: #ffdcb8;
}
#sun {
  width: 100%;
  top: 0;
  right: 0;
  position: absolute;
}
#sun > img {
  width: 80%;
  animation: motion 1.5s linear 0s infinite alternate;
  margin-top: 0;
  -webkit-animation: motion 1.5s linear 0s infinite alternate;
  margin-top: 0;
}
@keyframes motion {
  0% {
    margin-top: 0;
  }
  100% {
    margin-top: 10px;
  }
}

/* -webkit-@keyframes motion {
  0% {
    width: 80%;
    margin-top: 0;
  }
  100% {
    width: 85%;
    margin-top: 5px;
  }
} */
.titleImg {
  margin: 10px 0;
  position: absolute;
}
.titleImg > img {
  width: 100%;
  transition: all 0.2s linear;
}
.titleImg > img:hover {
  transform: scale(1.05);
}
.btn {
  border-radius: 50px;
  height: 50px;
}

@media (max-width: 766px) {
  .section-hero {
    height: 100vh;
    width: 100vw;
    background-image: url("../../../public/img/theme/mobilebg.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    width: 100%;
    position: absolute;
    background-size: cover;
  }
  #title {
    padding: 0;
  }
  .titleImg {
    margin: 0;
  }
  #sun > img {
    width: 30%;
    right: 0;
    position: absolute;
  }
  #titleSub {
    float: center;
    font-weight: 500;
    color: #989898;
    font-size: 1.3rem;
    text-align-last: center;
  }
}
@media (min-width: 767px) and (max-width: 1024px) {
  .titleImg {
    margin: 40px 0;
    width: 300px;
  }

  #sun {
    text-align: end;
  }
  .section-hero {
    height: 100vh;
    width: 100vw;
    background-image: url("../../../public/img/theme/mainbg_1024.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    width: 100%;
    position: absolute;
    background-size: cover;
  }
  #titleSub {
    font-size: 0.9rem;
  }
}
@media (min-width: 1025px) {
  .container {
    margin: 0 auto;
  }

  .section-hero {
    height: 100vh;
    width: 100vw;
    background-image: url("../../../public/img/theme/mainbg.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    width: 100%;
    position: absolute;
    background-size: cover;
  }
  #titleSub {
    padding: 0 48px;
  }
}
@media (min-heigth: 576px) {
  .titleImg > img {
    width: 85%;
  }
}
</style>

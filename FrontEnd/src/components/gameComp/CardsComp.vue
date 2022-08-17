<template>
  <div class="profile-page">
    <div id="wrap_msg" class="justify-content-center">
      <div v-if="dialog1 !== false" class="card_msg align-items-center">
        <p v-if="dialog1 === 'success'" class="text-center align-self-center">
          <b>정답입니다!</b>
        </p>
        <p v-else-if="dialog1 === 'fail'" class="text-center align-self-center">
          <b>다시 도전해보세요!</b>
        </p>
      </div>
    </div>
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class="py-5 px-md-5 card-profile mt--300" no-body>
          <!--카드 이름 나오는 곳 (1단계)-->
          <div v-if="dialog0" class="text-center card_name">
            <b>{{ this.solution }}</b>
          </div>
          <!--카드 이름 나오는 곳 (1단계)-->

          <div
            class="justify-content-center align-items-center my-5 py-5"
            v-if="!this.gameSet"
          >
            <h2 class="display-2">
              <div class="text-center mb-5">
                <b>글자와 맞는 그림을 찾아보아요!</b>
              </div>
            </h2>
            <div class="row justify-content-center">
              <base-button
                class="col-3 start_btn"
                id="startGameBtn"
                v-if="!this.gameSet"
                @click="createCards"
              >
                게임시작하기
              </base-button>
            </div>
          </div>

          <!--카드 이미지 -->
          <div v-if="this.selectedCards.length > 0" class="wrap_card mb-5">
            <h3></h3>
            <div id="cardsDiv" class="row">
              <div
                class="card col-3 p-0"
                id="gameCard"
                @click="reserve1(index)"
                :loading="loading[index]"
                v-for="(card, index) in selectedCards"
                :key="index"
              >
                <img
                  id="cardImg"
                  :src='card[1]'
                  alt="nothing"
                  class="img-fluid rounded shadow-lg"
                />
              </div>
            </div>
          </div>
          <!--카드 이미지 -->
        </card>
      </div>
    </section>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  components: {},

  data() {
    return {
      selectedCards: [],
      loading: [false, false, false],
      selection: 1,
      solution: [],
      dialog1: false,
      dialog0: false,

      gameCountPerGame: 0,
      gameCount: 0,
      successCount: 0,
      gameSet: false,

      timeStart: 0,
      timeEnd: 0,
      timeSequence: [],
      totalTime: null,
    };
  },
  methods: {
    getTimeNow() {
      let timeNow = new Date();
      let timeNowMilSec = timeNow.getTime();

      return timeNowMilSec;
    },

    createCards() {
      if (this.gameCount == 0) {
        this.timeSequence = []
      }
      this.gameSet = true;

      console.log(`올바른 카드를 고르세요`);
      this.$store.commit("sampleCards")
      setTimeout(() => {
        this.solution = this.$store.state.cardGame.solutionCard[0];
        console.log(`solution : ${this.solution}`);
        this.selectedCards = this.$store.state.cardGame.selectedCards;
        this.dialog0 = true;
      }, 1000);

      this.timeStart = this.getTimeNow();
      console.log(this.timeStart);
    },
    reserve1(index) {
      this.loading[index] = true;

      console.log(this.selectedCards[index][0]);
      console.log(`solution : ${this.solution}`);

      if (this.solution === this.selectedCards[index][0]) {
        this.timeEnd = this.getTimeNow();

        this.timeSequence.push(this.timeEnd - this.timeStart);

        console.log(this.timeSequence);

        setTimeout(() => (this.dialog1 = "success"));
        console.log("정답입니다!");
        this.gameCount += 1;
        this.selectedCards = [];

        if (this.gameCountPerGame === 0) {
          this.successCount += 1;
        }
        this.dialog1 = "false";

        if (this.gameCount === 1) {
          let totalTimeMilSec = this.timeSequence.reduce((a,b) => a + b, 0)

          let hour = parseInt(totalTimeMilSec / 3600000)

          let min = parseInt((totalTimeMilSec % 3600000) / 60000)

          let sec = parseInt((totalTimeMilSec % 60000) / 1000)
          
          this.totalTime = `${hour.toString().padStart(2, 0)}:${min.toString().padStart(2, 0)}:${sec.toString().padStart(2, 0)}`

          console.log(this.totalTime);
          console.log(this.successCount);
          let now = new Date()

          let dataSend = {
            score: this.successCount,
            totalTime: this.totalTime,
            childId: 'childId',
            createTime: `${now.getFullYear().toString().padStart(2, 0)}-${(now.getMonth() + 1).toString().padStart(2, 0)}-${now.getDate().toString().padStart(2, 0)}T${now.getHours().toString().padStart(2, 0)}:${now.getMinutes().toString().padStart(2, 0)}:${now.getSeconds().toString().padStart(2, 0)}`
          }
          
          console.log(dataSend);
          
          axios.post('https://i7a606.q.ssafy.io/service-api/play/result', dataSend)

          this.gameSet = false;
          this.gameCountPerGame = 0; 
          this.successCount = 0;
          this.gameCount = 0;
          this.timeSequence = [];
          
          
        } else {
          this.gameSet = true;
          this.gameCountPerGame = 0;
          setTimeout(() => this.createCards(), 3000);
        }
      } else {
        setTimeout(() => (this.dialog1 = "fail"));
        console.log("다시 골라보세요");
        this.gameCountPerGame += 1;
        this.dialog1 = "false";
        if (this.gameCount === 10) {
          this.gameSet = false;
        } else {
          this.gameSet = true;
        }
      }
      setTimeout(() => (this.loading[index] = false), 2000);
    },
  },
  watch: {
    dialog0(val) {
      if (!val) return;
      setTimeout(() => (this.dialog0 = false), 1000);
    },

    dialog1(val) {
      if (!val) return;

      setTimeout(() => (this.dialog1 = false), 2000);
    },
  },
};
</script>

<style scoped>
.card-profile {
  position: relative;
  height: 70vh;
}
.start_btn {
  height: 10vh;
  border-radius: 50px;
}
#cardImg {
  height: 20rem;
}
.card_msg {
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 999;
  position: absolute;
  background-color: rgba(102, 102, 102, 0.251);
  vertical-align: middle;
}
.card_msg > p {
  text-shadow: -2px 0 #fff, 0 2px #fff, 2px 0 #fff, 0 -2px #fff;
  color: brown;
  position: relative;
  top: 50%;
  transform: translateY(-50%);
  font-size: 5rem;
}
.card_name {
  display: flex;
  position: fixed;
  justify-content: center;
  left: 40%;
  bottom: 45%;
  height: 40vh;
  margin: 20px 0;
  font-size: 7rem;
}
#cardsDiv {
  margin-top: 5%;
  justify-content: space-around;
}

#cardImg {
  border-radius: 15px;
}
</style>

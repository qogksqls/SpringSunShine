<template>
  <div>
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
    <div class="profile-page">
      <section class="section-profile-cover section-shaped my-0">
        <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
      </section>
      <section class="section section-skew p-0">
        <div class="container">
          <card shadow class="py-5 px-md-5 card-profile mt--300" no-body>
            <!--카드 이름 나오는 곳 (1단계)-->
            <div class="cardshow">
              <div v-if="dialog0" class="text-center card_name">
                <b>{{ this.solution }}</b>
              </div>
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

                <base-button
                  v-if="!gameSet"
                  @click="endGame"
                  class="col-3 end_btn"
                  >게임 종료하기</base-button
                >
              </div>
            </div>

            <!--카드 이미지 -->
            <div v-if="this.selectedCards.length > 0" class="wrap_card mb-5">
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
                    :src="card[1]"
                    alt="Raised image"
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
  </div>
</template>

<script>
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
    };
  },
  methods: {
    endGame() {
      this.$store.state.cardGame.endGame = true;
    },
    getTimeNow() {
      let timeNow = new Date();
      let timeNowMilSec = timeNow.getTime();

      return timeNowMilSec;
    },

    createCards() {
      this.gameSet = true;

      console.log(`올바른 카드를 고르세요`);
      this.$store.commit("sampleCards");
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
      console.log(this.solution);

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

        if (this.gameCount === 3) {
          //this.$store.state.cardGame.playingNow = false
          console.log(this.successCount);
          axios.post("https://i7a606.q.ssafy.io/service-api/play/result", {
            score: this.successCount,
            totalTime: totalTime,
            childId: "childId",
            createTime: new Date(),
          });
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
/* 카드 이미지 들어간 곳 카드*/
.card-profile {
  padding: 5%;
  height: 80vh;
  cursor: pointer;
}
#cardsDiv {
  margin-top: 5%;
  justify-content: space-around;
}
/* 카드 명칭 뜨는 곳 */
.cardshow {
  position: absolute;
  z-index: 999;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background-color: #fdffbc;
}
.card_name {
  width: 100vw;
  height: 100vh;
  margin: 20px 0;
  font-size: 7rem;
  color: rgb(0, 0, 0);
}
/* 맨 첫페이지 (게임 시작하실?) */
.container {
  padding: 0;
}
.start_btn {
  height: 10vh;
  border-radius: 50px;
}
.end_btn {
  height: 10vh;
  border-radius: 50px;
}
/* 개개인 카드 사이즈 */
#cardImg {
  height: 17rem;
  border-radius: 15px;
}
/* 정답입니다/ 틀렸습니다 뜨는 곳 */
#wrap_msg {
  top: 0;
  left: 0;
  position: static;
}
.card_msg {
  top: 50%;
  left: 50%;
  width: 100vw;
  height: 115vh;
  z-index: 999;
  position: absolute;
  transform: translate(-50%, -50%);
  background-color: rgba(117, 0, 0, 0.251);
}
.card_msg > p {
  text-shadow: -2px 0 #fff, 0 2px #fff, 2px 0 #fff, 0 -2px #fff;
  color: brown;
  position: relative;
  top: 50%;
  transform: translateY(-50%);
  font-size: 5rem;
}
/* 뒷배경인데 건들지 마셈 */
.section-profile-cover {
  height: 250px;
}
</style>

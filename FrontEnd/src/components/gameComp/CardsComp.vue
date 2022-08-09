<template>
  <div>
    
    <div v-if="dialog1 !== false">
      <h1 v-if="dialog1 === 'success'">정답입니다!</h1>
      <h1 v-else-if="dialog1 === 'fail'">다시 도전해보세요!</h1>
    </div>

    <div v-if="dialog0">
      <h1>{{this.solution}}</h1>
    </div>
    
    <button
    id="startGameBtn"
    v-if="!this.gameSet"
    @click="createCards"
    >
      start game
    </button>
      
    <div v-if="this.selectedCards.length > 0">

      <div
      id="cardsDiv"
      >
      
        <div 
        class="card" 
        id="gameCard"
        @click="reserve1(index)"
        :loading="loading[index]"
        v-for="(card, index) in selectedCards"
        :key="index"
        >

          <img
          id="cardImg"
          :src="require(`@/assets/${card[1]}.jpg`)"
          >

        </div>

      </div>  
      
    </div>


  </div>
</template>

<script>

export default {

  components: {
  },
  
  data () {
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
    }
  },
  methods: {
    getTimeNow() {
      let timeNow = new Date()
      let timeNowMilSec = timeNow.getTime()

      return timeNowMilSec
    },

    createCards () {
      this.gameSet = true
      console.log(`올바른 카드를 고르세요`)
      this.$store.commit('sampleCards')
      this.solution = this.$store.state.cardGame.solutionCard[0]
      console.log(this.solution);
      setTimeout(() => {
        this.selectedCards = this.$store.state.cardGame.selectedCards
        }
      , 1000)

      this.timeStart = this.getTimeNow()
      console.log(this.timeStart)
      this.dialog0 = true
    },
    reserve1 (index) {
      this.loading[index] = true

      console.log(this.selectedCards[index][0])
      console.log(this.solution)

      if (this.solution === this.selectedCards[index][0]) {
        this.timeEnd = this.getTimeNow()

        this.timeSequence.push(this.timeEnd - this.timeStart)

        console.log(this.timeSequence)

        setTimeout(() => this.dialog1 = 'success')
        console.log('정답입니다!');
        this.gameCount += 1
        this.selectedCards = []

        if (this.gameCountPerGame === 0) {
          this.successCount += 1
        }
        this.dialog1 = 'false'

        if (this.gameCount === 10) {
          console.log(this.successCount)
          this.gameSet=false
          this.gameCountPerGame = 0
          this.successCount = 0
          this.gameCount = 0
          this.timeSequence = []
        } else {
          this.gameSet=true
          this.gameCountPerGame = 0
          setTimeout(() => this.createCards(), 3000)
         
        }

      } else {
        setTimeout(() => this.dialog1 = 'fail')
        console.log('다시 골라보세요')
        this.gameCountPerGame += 1
        this.dialog1 = 'false'
        if(this.gameCount === 10) {
          this.gameSet=false
        } else {
          this.gameSet=true
        }
      }
      setTimeout(() => (this.loading[index] = false), 2000)
    },
  },
  watch: {
    dialog0 (val) {
      if (!val) return

      setTimeout(() => (this.dialog0 = false), 1000)
    },

    dialog1 (val) {
      if (!val) return

      setTimeout(() => (this.dialog1 = false), 2000)
    },
    
  }
}
</script>

<style scoped>
#cardImg{
  width: 18rem;
  height: 27rem;
}

#cardsDiv{
  margin-top: 5%;
  width: 100%;
  display: flex;
  justify-content: space-around;
}

#cardImg{
  border: solid black 5px;
}
</style>

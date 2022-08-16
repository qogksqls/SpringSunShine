import _ from 'lodash'
import axios from 'axios'
export default {
  state: {

  playingNow: false,
  endGame: false,

  cardsName: [

  ],

  cardsImage: [

  ],
  
    selectedCards: [],
    solutionCard: [],
  },
  getters: {
  },
  mutations: {
    sampleCards (state) {

      axios.get('https://i7a606.q.ssafy.io/service-api/play/objectcard')
      .then( res => {
        for (let data of res.data) {
          console.log(data);
          state.cardsName.push(data.name)
          state.cardsImage.push(data.image)
        }
      })
      .catch( err => console.log(err))
    }
  },
  actions: {
  },
  modules: {
  }
}

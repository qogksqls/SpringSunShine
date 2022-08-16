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
        let tempName = []
        let tempImage = []
        let tempSelectedCards = []

        for (let data of res.data) {
          console.log(data);
          tempName.push(data.name)
          tempImage.push(data.image)

          tempSelectedCards.push([data.name, data.image, data.question])
        }
        state.cardsName = tempName
        state.cardsImage = tempImage
        state.selectedCards = tempSelectedCards
        state.solutionCard = _.sample(state.selectedCards)
      })
      .catch( err => console.log(err))
    }
  },
  actions: {
  },
  modules: {
  }
}

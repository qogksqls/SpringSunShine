import _ from 'lodash'
import axios from 'axios'
export default {
  state: {

  playingNow: false,
  endGame: false,

  cardsName: [
    '사자',
    '호랑이',
    '펭귄',
    '강아지',
    '고양이',
  ],

  cardsImage: [
    'lion',
    'tiger',
    'penguin',
    'dog',
    'cat'
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
        for (data of res.data) {
          state.cardsName.push(data.name)
          state.cardsImage.push(data.image)
        }
      })
    }
  },
  actions: {
  },
  modules: {
  }
}

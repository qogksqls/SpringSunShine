import _ from 'lodash'

export default {
  state: {

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

      let tempList = []
      let sampledIndex = _.sampleSize(_.range(state.cardsName.length), 3)
      
      for (let index of sampledIndex) {
        tempList.push([state.cardsName[index], state.cardsImage[index]])
        state.selectedCards = tempList
      }
      
      state.solutionCard = _.sample(state.selectedCards)
    }
  },
  actions: {
  },
  modules: {
  }
}

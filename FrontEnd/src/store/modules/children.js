const childrenstore = {
  state: {
  },
  getters: {
    GET_CHILD: state => state.children,
  },
  mutations: {
    ADD_CHILD: (state, payload) => {
        // console.log(payload)
        state.children.push(payload)
        // console.log(state.children)
    },
    DELETE_CHILD: (state, index) => {
        state.children.splice(index, 1)
    }
  },
  actions: {
  },
  modules: {
  },
}
  
  export default childrenstore
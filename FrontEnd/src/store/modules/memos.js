
const memoStore = {
    state: {
        list: [],
    },
    getters: {
        GET_MEMO: state => state.list,
    },
    mutations: {
        ADD_MEMO: (state, payload) => {
            state.list.push(payload)
        },
        DELETE_MEMO: (state, index) => {
            state.list.splice(index, 1)
        },
    },
    actions: {
    },
    modules: {
    },
  }
  
  export default memoStore
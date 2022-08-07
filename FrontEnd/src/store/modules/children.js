const childrenstore = {
    state: {
        children: []
    },
    getters: {
        GET_CHILD: state => state.children,
    },
    mutations: {
        ADD_CHILD: (state, payload) => {
            state.children.push(payload)
        },
        DELETE_CHILD: (state, index) => {
            state.list.splice(index, 1)
        }
    },
    actions: {
    },
    modules: {
    },
  }
  
  export default childrenstore
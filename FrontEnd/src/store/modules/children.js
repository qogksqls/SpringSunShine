const childrenstore = {
    state: {
        children: [
            {
                이름: "최지우",
                생년월일: "2016-10-10",
                성별: "여",
                문진표: false
            },
            {
                이름: "남기철",
                생년월일: "2018-08-08",
                성별: "남",
                문진표: true
            }
        ]
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
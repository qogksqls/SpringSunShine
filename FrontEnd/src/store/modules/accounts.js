import axios from "axios"

const userStore = {
    state: {
        host: 'https://a606.shop',
        userId: '',
        userName: '',
        token: '',
        role: '',
    },
    getters: {
    },
    mutations: {
        loginToken(state, payload) {
            state.token = payload.accessToken
            state.userId = payload.userid
        },
        logout(state) {
            if (state.token) {
                state.token = ''
                state.userId = ''
                alert('로그아웃되었습니다.')
            }
        },
        // loginCheck(state) {
        //     axios.get(`${state.host}/`, {
        //         headers: {
        //             "token": state.token
        //         }
        //     })
        //     .then(
        //         res => {
        //             console.log(res)
        //             state.role = res.data.token.role
        //         },
        //         error => {
        //             console.log('로그인 정보 없음')
        //             router.push("/login")
        //         }
        //     )
        // }
    },
    actions: {
        login({ commit, dispatch }, credentials) {
            axios({
                url: `${state.host}/auth-api/auth/login`,
                method: 'post',
                data: credentials
            })
            .then(res => {
                const token = res.data.key
                console.log(token)
                dispatch('saveToken', token)
                dispatch('fetchCurrentUser')
                router.push({ name: 'components' })
            })
            .catch(err => {
                console.log(err.response.data)
                commit('SET_AUTH_ERROR', err.response.data)
            })
        }
    },
    modules: {
    },
  }
  
  export default userStore
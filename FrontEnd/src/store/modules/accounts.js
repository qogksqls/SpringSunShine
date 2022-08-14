import axios from "axios"

const userStore = {
    state: {
        // token: localStorage.getItem('token') || '' ,
        currentUser: {},
        profile: {},
        authError: null,
        token: '',
        userid: ''
    },
    getters: {
        isLoggedIn: state => !!state.token,
        currentUser: state => state.currentUser,
        profile: state => state.profile,
        authError: state => state.authError,
        // authHeader: state => ({ Authorization: `Token ${state.token}`}),
    },
    mutations: {
        SET_TOKEN: (state, token) => state.token = token,
        SET_CURRENT_USER: (state, user) => state.currentUser = user,
        SET_PROFILE: (state, profile) => state.profile = profile,
        SET_AUTH_ERROR: (state, error) => state.authError = error,

        loginToken(state, payload) {
            state.token = payload.accessToken
            state.userid = payload.userid
        },
    },
    actions: {
        saveToken({ commit }, token) {
            commit('SET_TOKEN', token)
            localStorage.setItem('token', token)
        },
        removeToken({ commit }) {
            commit('SET_TOKEN', '')
            localStorage.setItem('token', '')
        },

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
        },

        fetchCurrentUser({ commit, getters, dispatch }) {
            if (getters.isLoggedIn) {
              axios({
                url: `${state.host}/auth-api/user/${state.userid}`,
                method: 'get',
              })
                .then(res => commit('SET_CURRENT_USER', res.data, console.log(res)))
                .catch(err => {
                  if (err.response.status === 401) {
                    dispatch('removeToken')
                    router.push({ name: 'login' })
                  }
                })
            }
        },
    },
}
  
  export default userStore
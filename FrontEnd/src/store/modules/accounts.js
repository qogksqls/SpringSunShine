import axios from "axios";
import router from "../../router";

export default {
  state: {
    accessToken: localStorage.getItem("accessToken") || '',
    refreshToken: localStorage.getItem("refreshToken") || '',
    userid: localStorage.getItem("userid") || '',
    childInfo: {},
    parentInfo: {},
    theraInfo: {},
    currentUser: {},
    profile: {},
    authError: null,
  },
  getters: {
    isLoggedIn: (state) => !!state.accessToken,
    currentUser: (state) => state.currentUser,
    profile: (state) => state.profile,
    authError: (state) => state.authError,
  },
  mutations: {
    SET_Access_TOKEN: (state, accessToken) => (state.accessToken = accessToken),
    SET_Refresh_TOKEN: (state, refreshToken) =>
      (state.refreshToken = refreshToken),
    SET_USERID: (state, userid) => (state.userid = userid),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
    SET_PROFILE: (state, profile) => (state.profile = profile),
    SET_AUTH_ERROR: (state, error) => (state.authError = error),
  },
  actions: {
    saveAccessToken({ commit }, accessToken) {
      commit("SET_Access_TOKEN", accessToken);
      localStorage.setItem("accessToken", accessToken);
      // console.log(localStorage)
    },
    saveRefreshToken({ commit }, refreshToken) {
      commit("SET_Refresh_TOKEN", refreshToken);
      localStorage.setItem("refreshToken", refreshToken);
      // console.log(localStorage)
    },
    saveUserid({ commit }, userid) {
      commit("SET_USERID", userid);
      localStorage.setItem("userid", userid);
    },
    removeToken({ commit }) {
      commit("SET_Access_TOKEN", "");
      commit("SET_Refresh_TOKEN", "");
      localStorage.setItem("accessToken", "");
      localStorage.setItem("refreshToken", "");
    },
    removeUserid({ commit }) {
      commit("SET_USERID", "");
      localStorage.setItem("userid", "");
    },
    login({ commit, dispatch }, credentials) {
      console.log("로그인");
      console.log(credentials);
      // if (credentials.saveId) {
      //   this.$cookies.set("idCookie", credentials.id);
      // }
      axios({
        url: "https://i7a606.q.ssafy.io/auth-api/auth/login",
        method: "post",
        data: credentials,
      })
        .then((res) => {
          console.log(res.data);
          const accessToken = res.data.accessToken;
          const refreshToken = res.data.refreshToken;
          const userid = res.data.userid;
          dispatch("saveAccessToken", accessToken);
          dispatch("saveRefreshToken", refreshToken);
          dispatch("saveUserid", userid);
          // dispatch("fetchCurrentUser", userid);
          router.push({ name: "components" });
        })
        .catch((err) => {
          console.log(err.response);
          commit("SET_AUTH_ERROR", err.response.data);
        });
    },
    logout({ dispatch }) {
      // console.log(this.state.accounts.accessToken)
      if (!this.state.accounts.accessToken) {
        router.push({ name: "login" });
      }
      axios({
        url: "https://i7a606.q.ssafy.io/auth-api/auth/logout",
        method: "post",
        data: {
          accessToken: this.state.accounts.accessToken,
          refreshToken: this.state.accounts.refreshToken,
        },
      })
        .then((res) => {
          dispatch("removeToken");
          dispatch("removeUserid");
          alert("로그아웃 되었습니다.");
          router.push({ name: "login" });
        })
        .catch((err) => {
          console.log("로그아웃 실패!");
          console.log(this.state.accounts.accessToken);
          console.log(this.state.accounts.refreshToken);
          dispatch("removeToken");
          dispatch("removeUserid");
        });
    },
    updateToken({ commit, dispatch }) {
      axios({  // accessToken 체크
        url: `https://i7a606.q.ssafy.io/service-api/auth/refresh/${this.$store.state.accounts.userid}`,
        method: 'get',
        headers: { Authorization: `Bearer ${this.$store.state.accounts.refreshToken}`}
      })
        .then(res => {
          console.log(res.data)
          this.$store.state.accounts.accessToken = res.data.accessToken
        })
        .catch(err => {
          console.log(err.response)
        })
    },
    fetchCurrentUser({ commit, getters, dispatch }, userid) {
      if (getters.isLoggedIn) {
        console.log("currentUser: ")
        axios({
          url: `https://i7a606.q.ssafy.io/service-api/user/${userid}`,
          method: 'get',
          headers: { Authorization: `Bearer ${this.$store.state.accounts.accessToken}`}
        })
          .then((res) => {
            console.log(res.data);
            commit("SET_CURRENT_USER", res.data);
          })
          .catch((err) => {
            console.log(err.response);
            if (err.response.status === 401) {
              dispatch("removeToken");
              router.push({ name: "components" });
            }
          });
      }
    },
    // signup({ commit, dispatch }, credentials) {
    //   console.log(credentials)
    //   axios({
    //     url: `${this.$store.state.host}/service-api/user/therapist`,
    //     method: 'post',
    //     data: credentials
    //   })
    //     .then(res => {
    //       console.log(res.data)
    //       router.push({ name: 'login' })
    //     })
    //     .catch(err => {
    //       console.log(err)
    //       commit('SET_AUTH_ERROR', err.response.data)
    //     })
    // },
  },
};

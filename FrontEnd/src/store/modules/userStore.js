import router from "@/router";

const userStore = {
  state: {
    id: "",
    userName: "",
    token: "",
  },
  mutations: {
    login: function(state, payload) {
      state.id = payload.userId;
      state.userName = payload.userName;
      state.token = payload.token;
    },
    loginCheck: function(state) {
      if (!state.token) {
        router
          .push({
            name: "login",
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};

export default userStore;

import Vue from "vue";
import Vuex from "vuex";

import children from "./modules/children";
import memos from "./modules/memos";
import teacher from "./modules/teacher";
import accounts from "./modules/accounts";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    host: "https://i7a606.q.ssafy.io",
  },
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    children,
    memos,
    teacher,
    accounts,
  },
});

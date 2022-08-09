import Vue from "vue";
import Vuex from "vuex";

import children from "./modules/children";
import userStore from "./modules/userStore";
import memos from "./modules/memos";
import cardGame from "./modules/cardGame"
Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: { userStore, memos, children, cardGame },
  plugins: [],
});

import Vue from "vue";
import Router from "vue-router";
import AppHeader from "./layout/AppHeader";
import AppFooter from "./layout/AppFooter";
import Components from "./views/Components.vue";
import Landing from "./views/Landing.vue";
import Login from "./views/Login.vue";
import Register from "./views/Register.vue";
import Profile from "./views/Profile.vue";
import SigninSelect from "./views/components/Signin/SigninSelect.vue";
import CounselorRecommend from "./views/RecommendView/CounselorRecommend.vue";

// child
import childList from '@/views/ChildList.vue'

// survey
import SurveyView from '@/views/survey/SurveyView'
import SurveyResultView from '@/views/survey/SurveyResultView'

// consult
import memoView from '@/views/consult/MemoView.vue'

// reserve
import reserve from '@/views/Reserve/ReserveView'


Vue.use(Router);

export default new Router({
  linkExactActiveClass: "active",
  routes: [
    {
      path: "/",
      name: "components",
      components: {
        header: AppHeader,
        default: Components,
        footer: AppFooter,
      },
    },
    {
      path: "/landing",
      name: "landing",
      components: {
        header: AppHeader,
        default: Landing,
        footer: AppFooter,
      },
    },
    {
      path: "/login",
      name: "login",
      components: {
        header: AppHeader,
        default: Login,
        footer: AppFooter,
      },
    },
    {
      path: "/register",
      name: "register",
      components: {
        header: AppHeader,
        default: Register,
        footer: AppFooter,
      },
    },
    {
      path: "/profile",
      name: "profile",
      components: {
        header: AppHeader,
        default: Profile,
        footer: AppFooter,
      },
    },
    {
      path: "/signinSelect",
      name: "signinSelect",
      components: {
        header: AppHeader,
        default: SigninSelect,
      },
    },
    {
      path: "/counselorRecommend",
      name: "counselorRecommend",
      components: {
        header: AppHeader,
        default: CounselorRecommend,
      },
    },
    // child
    {
      path: "/child",
      name: "child",
      components: {
        header: AppHeader,
        default: childList,
      },
    },
    // survey
    {
      path: "/question",
      name: "question",
      components: {
        header: AppHeader,
        default: SurveyView,
      },
    },
    {
      path: "/question/result",
      name: "questionresult",
      components: {
        header: AppHeader,
        default: SurveyResultView,
      },
    },
    // consult
    {
      path: "/memo",
      name: "memo",
      components: {
        header: AppHeader,
        default: memoView,
      },
    },
    // reserve
    {
      path: "/reserv-parent",
      name: "reserv-parent",
      components: {
        header: AppHeader,
        default: reserve,
      },
    },

  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});

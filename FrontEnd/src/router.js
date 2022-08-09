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
import ChildReserveShow from "./views/ReserveView/ChildReserveShowView.vue";

//아동추가 목록
import ChildListView from "./views/ChildPlusView/ChildListView.vue";
//문진표
import SurveyView from "./views/survey/SurveyView";
import SurveyResultView from "./views/survey/SurveyResultView";
//webRTC
import WebStudent from "./views/WebRtcView/WebRtcStudentView.vue";
import WebCounselor from "./views/WebRtcView/WebRtcCounselorView.vue";

import reserve from "./views/Reserve/ReserveView.vue"

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
    {
      path: "/childReserveShow",
      name: "childReserveShow",
      components: {
        header: AppHeader,
        default: ChildReserveShow,
      },
    },
    //아동추가페이지
    {
      path: "/children",
      name: "children",
      components: {
        header: AppHeader,
        default: ChildListView,
      },
    },
    //문단표
    {
      path: "/survey",
      name: "survey",
      components: {
        header: AppHeader,
        default: SurveyView,
      },
    },
    {
      path: "/survey/result",
      name: "surveyresult",
      components: {
        header: AppHeader,
        default: SurveyResultView,
      },
      props: true,
    },
    //webRTC
    {
      path: "/webStudent",
      name: "webStudent",
      component: WebStudent,
    },
    {
      path: "/webCounselor",
      name: "webCounselor",
      component: WebCounselor,
    },
    //reserve
    {
      path: "/resev-parent",
      name: "reserve",
      component: reserve,
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

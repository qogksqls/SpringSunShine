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

//상담관련
import ChildReserveShow from "./views/ReserveView/ChildReserveShowView.vue";
import ChildReserveShowCounselor from "./views/ReserveView/ChildReserveShowCounselor.vue";
import CounSchedule from "./views/ReserveView/ConsultationSchedule.vue";
//상담 후기 작성
import CounReview from "./views/ConsultReview/MemoReview.vue";
//아동추가 목록
import ChildListView from "./views/ChildPlusView/ChildListView.vue";
//문진표
import SurveyView from "./views/survey/SurveyView";
import SurveyResultView from "./views/survey/SurveyResultView";
//webRTC
import WebStudent from "./views/WebRtcView/WebRtcStudentView.vue";
import WebCounselor from "./views/WebRtcView/WebRtcCounselorView.vue";
//cardGame
import playAlone from "./views/playAloneView/playAlone.vue";

import reserve from "./views/Reserve/ReserveView.vue";

import memo from "./views/consult/MemoView.vue";

//회원정보수정
import update from "./views/UserInfoView/UserUpdateForm.vue";

//mainpage
import video from "./components/subMain/subMain.vue";
Vue.use(Router);

export default new Router({
  mode: "history",
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
      },
    },
    {
      path: "/video",
      name: "video",
      components: {
        header: AppHeader,
        default: video,
      },
    },
    {
      path: "/login",
      name: "login",
      components: {
        header: AppHeader,
        default: Login,
      },
    },
    //회원가입
    {
      path: "/register",
      name: "register",
      components: {
        header: AppHeader,
        default: Register,
      },
    },
    //회원정보수정
    {
      path: "/update",
      name: "update",
      components: {
        header: AppHeader,
        default: update,
      },
    },
    {
      path: "/profile",
      name: "profile",
      components: {
        header: AppHeader,
        default: Profile,
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

    //상담예약 관련
    {
      path: "/childReserveShow",
      name: "childReserveShow",
      components: {
        header: AppHeader,
        default: ChildReserveShow,
      },
    },
    {
      path: "/childReserveShowCounselor",
      name: "childReserveShowCounselor",
      components: {
        header: AppHeader,
        default: ChildReserveShowCounselor,
      },
    },
    //상담사 상담 후기 작성하는 곳
    {
      path: "/counReview",
      name: "counReview",
      components: {
        header: AppHeader,
        default: CounReview,
      },
    },
    //상담사 예약 일정
    {
      path: "/counSchedule",
      name: "counSchedule",
      components: {
        header: AppHeader,
        default: CounSchedule,
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

    //혼자놀기
    {
      path: "/playAlone",
      name: "playAlone",
      components: {
        header: AppHeader,
        default: playAlone,
      },
    },

    //reserve
    {
      path: "/resev-parent",
      name: "reserve",
      components: {
        header: AppHeader,
        default: reserve,
      },
      props: true,
    },

    //메모
    {
      path: "/memo",
      name: "memo",
      components: {
        header: AppHeader,
        default: memo,
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

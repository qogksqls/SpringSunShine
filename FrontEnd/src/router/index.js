import Vue from 'vue'
import VueRouter from 'vue-router'

// baseview
import HomeView from '../views/baseview/HomeView.vue'
import LoginView from '../views/baseview/LoginView.vue'
import AccountView from '../views/baseview/AccountView.vue'
import IntroView from '../views/baseview/IntroView.vue'

// articleview
import ArticleDetailView from '../views/articleview/ArticleDetailView'
import ArticleEditView from '../views/articleview/ArticleEditView'
import ArticleListView from '../views/articleview/ArticleListView'
import ArticleNewView from '../views/articleview/ArticleNewView'

// mypageview
import MypageView from '../views/mypageview/MypageView'

// reserveview
import ReserveRecommendView from '../views/reserveview/ReserveRecommendView'
import ReserveDateView from '../views/reserveview/ReserveDateView'

// survey
import SurveyView from '@/views/survey/SurveyView'
import SurveyResultView from '@/views/survey/SurveyResultView'

Vue.use(VueRouter)

const routes = [
  // baseview
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/account',
    name: 'account',
    component: AccountView
  },
  {
    path: '/intro',
    name: 'intro',
    component: IntroView
  },

  // articleview
  {
    path: '/articles',
    name: 'articles',
    component: ArticleListView
  },
  {
    path: '/articles/new',
    name: 'articleNew',
    component: ArticleNewView
  },
  {
    path: '/articles/:articlePk',
    name: 'article',
    component: ArticleDetailView
  },
  {
    path: '/articles/:articlePk/edit',
    name: 'articleEdit',
    component: ArticleEditView,
  },

  // mypageview
  {
    path: '/mypages/:userPk',
    name: 'mypage',
    component: MypageView,
  },
  
  // reserveview
  {
    path: '/reserve/recommend',
    name: 'reserveRecommend',
    component: ReserveRecommendView
  },
  {
    path: '/reserve/date',
    name: 'reserveDate',
    component: ReserveDateView
  },
  // survey
  {
    path: '/survey',
    name: 'survey',
    component: SurveyView,
  },
  {
    path: '/survey/result',
    name: 'surveyresult',
    component: SurveyResultView,
    props: true,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

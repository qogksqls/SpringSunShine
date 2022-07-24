<template>
  <div>
    <nav-bar-comp></nav-bar-comp>
    <!-- <home-view></home-view>
    <intro-view></intro-view> -->
    <!-- <mypage-view></mypage-view> -->
    <router-view></router-view>
  </div>
</template>

<script>
  import NavBarComp from './components/NavBarComp.vue'
  // import MypageView from './views/mypage/MypageView.vue'
  // import HomeView from '@/views/HomeView.vue'
  // import IntroView from '@/views/IntroView.vue'


  export default {
    components: {
      NavBarComp,
      // MypageView,
      // HomeView,
      // IntroView,
    },
  }
</script>

<style scoped>
@font-face {
    font-family: 'GangwonEdu_OTFBoldA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
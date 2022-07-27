<template>
  <div>
    <nav-bar-comp></nav-bar-comp>
    <div>
      <img id="background" src="@/assets/배경4.jpg" alt="home_background">
      <img id="background" src="@/assets/배경4.jpg" alt="home_background">
      <img id="background" src="@/assets/배경4.jpg" alt="home_background">
    </div>
    <router-view style="position: flex;"></router-view>
  </div>
</template>

<script>
  import NavBarComp from './components/basecomp/NavBarComp.vue'


  export default {
    components: {
      NavBarComp,
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
#background {
  margin-bottom: -5px;
  width: 100%;
}
</style>
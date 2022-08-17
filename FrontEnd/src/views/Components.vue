<template>
  <div>
    <hero></hero>
    <!-- <basic-elements></basic-elements>
        <inputs></inputs>
        <custom-controls></custom-controls>
        <navigation></navigation>
        <javascript-components></javascript-components>
        <icons></icons>
        <examples></examples>
        <download-section></download-section>
        <carousel></carousel> -->
  </div>
</template>
<script>
import Hero from "./components/Hero";
import BasicElements from "./components/BasicElements";
import Inputs from "./components/Inputs";
import CustomControls from "./components/CustomControls";
import Navigation from "./components/Navigation";
import JavascriptComponents from "./components/JavascriptComponents";
import Carousel from "./components/Carousel";
import Icons from "./components/Icons";
import Examples from "./components/Examples";
import DownloadSection from "./components/DownloadSection";

export default {
  name: "components",
  components: {
    Hero,
    BasicElements,
    Inputs,
    CustomControls,
    Navigation,
    JavascriptComponents,
    Carousel,
    Icons,
    Examples,
    DownloadSection,
  },
  created() {
    if (this.$store.state.accounts.refreshToken && !this.$store.state.accounts.accessToken) {
      axios({
        url: `https://i7a606.q.ssafy.io/service-api/auth/refresh/${this.$store.state.accounts.userid}`,
        method: 'get'
      })
        .then(res => {
          console.log(res.data)
        })
        .catch(err => {
          console.log(err.response)
        })
    }
    // else { // 로그인이 안되어 있다면 Login페이지로 이동
    //   this.$router.push({name : 'login'})
    // }
  }
};
</script>

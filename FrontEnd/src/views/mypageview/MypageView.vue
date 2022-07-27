<template>
  <div>
    <nav-bar-comp></nav-bar-comp>
    <mypage-comp></mypage-comp>
  </div>
</template>

<script>
import NavBarComp from "../../components/basecomp/NavBarComp.vue"
import MypageComp from "../../components/mypagecomp/MypageComp.vue"

export default {
  components: {
    NavBarComp,
    MypageComp,
  }
}
</script>

<style>

</style>
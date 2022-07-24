<template>
  <div>
    <nav-bar-comp></nav-bar-comp>
    <mypage-comp></mypage-comp>
  </div>
</template>

<script>
import NavBarComp from "../../components/NavBarComp.vue"
import MypageComp from "../../components/mypage/MypageComp.vue"

export default {
  components: {
    NavBarComp,
    MypageComp,
  }
}
</script>

<style>

</style>
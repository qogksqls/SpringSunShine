<template>
  <div class="login">
    <login-comp></login-comp>
  </div>
</template>

<script>
import LoginComp from "../../components/basecomp/LoginComp.vue"
  export default {
    components: {
      LoginComp,
    },
  }
</script>

<style>
.login {
  position: absolute;
  top: 0;
	margin-top: 10rem;
  width: 100%;
}
</style>
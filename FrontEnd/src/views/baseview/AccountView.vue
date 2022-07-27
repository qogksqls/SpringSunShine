<template>
  <div class="signup">
    <account-comp></account-comp>
  </div>
</template>

<script>
import AccountComp from "../../components/basecomp/AccountComp.vue"
export default {
  components: {
    AccountComp,
  }
}
</script>

<style>
.signup {
  position: absolute;
  top: 0;
	margin-top: 10rem;
  width: 100%;
}

</style>
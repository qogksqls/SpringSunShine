<template>
  <div class="reserve">
    <div class="reserve-comp">
      <reserve-comp></reserve-comp>
    </div>
  </div>
</template>

<script>
import reserveComp from "@/components/Reserve/ReserveComp.vue";

export default {
  components: { reserveComp },
  created() {
    
    this.$axios({  // accessToken 재발급
      url: `https://i7a606.q.ssafy.io/service-api/auth/refresh/${this.$store.state.accounts.userid}`,
      method: 'get',
      headers: { Authorization: `Bearer ${this.$store.state.accounts.refreshToken}`}
    })
      .then(res => {
        console.log(res.data)
        this.$store.state.accounts.accessToken = res.data.accessToken
      })
      .catch(err => {
        console.log(err.response)
      })
  }
};
</script>

<style></style>

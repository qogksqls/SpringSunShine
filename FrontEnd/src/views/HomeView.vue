<template>
  <div>
    <div v-if="login === true">
      <h1>hello {{ name }}</h1>
      <h1>id : {{ userId }}</h1>
      <h1>position : {{ position }}</h1>
      <h1>department : {{ department }}</h1>
    </div>
    <h1>Home</h1>
  </div>
</template>

<script>
export default {
  computed: {
    check_login () {
      return this.$store.state.userStore.login
    }
  },
  data () {
    return {
      'userId': this.$store.state.userStore.userId,
      'name': this.$store.state.userStore.name,
      'position': this.$store.state.userStore.position,
      'department': this.$store.state.userStore.department,
      'login': false,
      }
    },
  watch: {
    check_login (val) {
      this.login = val
      }
    }
  }

</script>

<style>

</style>
<template>
  <div class="home">
    <div v-if="login === true">
      <h1>hello {{ name }}</h1>
      <h1>id : {{ userId }}</h1>
      <h1>position : {{ position }}</h1>
      <h1>department : {{ department }}</h1>
    </div>
    <div>
      <h1>home</h1>
      <!-- <img id="background" src="@/assets/배경4.jpg" alt="home_background">
      <img id="background" src="@/assets/배경4.jpg" alt="home_background">
      <img id="background" src="@/assets/배경4.jpg" alt="home_background"> -->
      <!-- <span class="imtext">
        <p>안녕하세요.</p>
        <p>봄날의 햇살입니다.</p>
      </span> -->
    </div>
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
.home {
  position: absolute;
  top: 0;
  margin-top: 10rem;
  margin-bottom: -5px;
}

.imtext {
  font-size: 25px;
  font-family: 'GangwonEdu_OTFBoldA';
  position: absolute;
  top: 20%;
  right: 15%;
  z-index: 1;
}
</style>
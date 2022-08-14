<template>
  <div id="wrap">
    <h3>
      <div class="top_naming text-left mb-5">
        <h1>"{{ this.$route.params.name }}" 아동</h1>
        <b id="disease_name">"{{ name }}"</b>
        담당 상담사들입니다.
      </div>
    </h3>
    <div class="row justify-content-center mt-4 text-center">
      <div v-for="(counselor, i) in childsCounselors" :key="i">
        <circleProfile :counselor="counselor" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import circleProfile from "./CircleProfile.vue";

export default {
  name: 'CounselorUpper',
  components: { circleProfile },
  props: ['childsCounselors'],
  data() {
    return {
      name: ''
    }
  },
  created() {
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/answer/getAnswer/${this.$route.params.childId}`,
      method: 'get',
    })
      .then(res => {
        // console.log(res.data)
        this.name = res.data['expertise']
      })
      .catch(err => {
        console.log(err.response)
      })
  }
};
</script>

<style scoped>
img {
  width: 180px;
}
.profile-page .card-profile .card-profile-image img {
  position: relative;
}
.card_about p {
  margin-bottom: 1px;
  color: #000;
}
</style>

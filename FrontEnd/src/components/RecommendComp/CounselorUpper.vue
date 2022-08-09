<template>
  <div id="wrap">
    <h3>
      <div class="top_naming text-left mb-5">
        <b>추천</b>
        <b id="disease_name">"병명"</b>
        <b>증상분야상담사</b>
      </div>
    </h3>
    <div class="row justify-content-center mt-4 text-center">
      <!--몇개 들어갈지 안정함 -> 전문가 수에 따라 달라지게 할듯?-->

      <!--전문가 한명-->
      <circleProfile />
      <circleProfile />
      <circleProfile />
      <circleProfile />
    </div>
  </div>
</template>

<script>
import circleProfile from "./CircleProfile.vue";

export default {
  components: { circleProfile },
  data() {
    return {};
  },
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

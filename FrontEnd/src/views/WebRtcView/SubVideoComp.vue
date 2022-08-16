<template>
  <div>
    <div id="video-container" class="sub_vid">
      <user-video :stream-manager="subStreamManager" />
    </div>
  </div>
</template>

<script>
import UserVideo from "./UserVideo.vue";

export default {
  props: {
    subStreamManager: Object,
  },

  components: {
    UserVideo,
  },
};
</script>

<style scoped></style>

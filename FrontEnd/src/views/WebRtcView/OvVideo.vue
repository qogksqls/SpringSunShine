<template>
  <video autoplay />
  <!-- <video :style="styleObject" autoplay/> -->
</template>

<script>
export default {
  name: "OvVideo",

  // data () {
  // 	return {
  // 		styleObject: {
  // 			widthOfVideo: this.sizeOfVideo.widthOfVideo,
  // 			heightOfVideo: this.sizeOfVideo.heightOfVideo,
  // 		}
  // 	}
  // },
  props: {
    streamManager: Object,
    // widthOfVideo: Number,
    // heightOfVideo: Number,
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>

<style scoped>
video {
  border: 2px solid #fff;
  width: 100%;
  border-radius: 15px;
}
</style>

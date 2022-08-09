<template>
  <div id="main-video" class="wrap_cont">

    <user-video :widthOfVideo="widthOfVideo" :heightOfVideo="heightOfVideo" :stream-manager="mainStreamManager"/>

  </div>
</template>

<script>
import UserVideo from "./UserVideo"

export default {
  props: {
    mainStreamManager: Object,
    widthOfVideo: Number,
    heightOfVideo: Number,
  },

  components: {
    UserVideo,
  },
}
</script>

<style>

</style>
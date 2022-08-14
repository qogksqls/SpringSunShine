<template>
  <div id="main-video" class="wrap_cont">

    <!-- <user-video :widthOfVideo="widthOfVideo" :heightOfVideo="heightOfVideo" :stream-manager="mainStreamManager"/> -->
    <user-video :stream-manager="mainStreamManager"/>

  </div>
</template>

<script>
import UserVideo from "./UserVideo"

export default {
  name: 'MainVideo',

  props: {
    mainStreamManager: Object,
    // widthOfVideo: Number,
    // heightOfVideo: Number,
  },

  components: {
    UserVideo,
  },
}
</script>

<style>

</style>
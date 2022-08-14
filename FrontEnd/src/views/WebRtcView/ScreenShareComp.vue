<template>
  <div id="screen-share" class="wrap_cont">

    <user-video :stream-manager="sessionScreen"/>

  </div>
</template>

<script>
import UserVideo from "./UserVideo"

export default {
  name: 'ScreenShare',

  props: {
    sessionScreen: Object,
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
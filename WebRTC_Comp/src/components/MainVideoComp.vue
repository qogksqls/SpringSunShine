<template>
  <div id="main-video" class="wrap_cont">

    <user-video :stream-manager="mainStreamManager"/>

  </div>
</template>

<script>
import UserVideo from "./UserVideo"

export default {
  props: {
    mainStreamManager: Object,
  },

  components: {
    UserVideo,
  },
}
</script>

<style>

</style>
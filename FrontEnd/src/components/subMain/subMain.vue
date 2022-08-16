<template>
  <section class="section-hero section-shaped my-0">
    <div class="shape shape-style-1 shape-primary"></div>
    <div class="container">
      <div class="video">
        <video src="../../../public/video.mp4" muted autoplay loop></video>
        <div class="backgd"></div>
      </div>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
.section-hero {
  height: 100%;
}
.video {
  width: 100%;
  height: 100%;
}
.backgd {
  width: 100%;
  height: 100%;
  background: linear-gradient(to left, #037c937b 10%, #0e0300 94%);
}
video {
  width: 100%;
  height: 100vh;
  object-fit: fill;
}
</style>

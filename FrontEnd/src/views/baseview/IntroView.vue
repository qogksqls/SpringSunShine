<template>
<v-app>
  <div class="carousel">
    <v-carousel>
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        :src="item.src"
        reverse-transition="fade-transition"
        transition="fade-transition"
      ></v-carousel-item>
    </v-carousel>
  </div>
</v-app>
</template>

<script>
  export default {
    data () {
      return {
        items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
        ],
      }
    },
  }
</script>

<style>
/* .v-window__next {
  background:rgba(0,0,0,0.15);
  position: absolute;
  height:68px;
  width:68px;
  top:calc(50% - 33px);
  margin-right:13%;
}
.v-window__prev {
  background:rgba(0,0,0,0.15);
  position: absolute;
  height:68px;
  width:68px;
  top:calc(50% - 33px);
  margin-left:13%;
} */
.carousel {
  position: absolute;
  top: 20%;
  width: 100%;
  height: 100%;
}
</style>
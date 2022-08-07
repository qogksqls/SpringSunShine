<template>
  <div class="reserve">
    <div class="reserve-comp">
      <reserve-comp></reserve-comp>
    </div>
  </div>
</template>

<script>
import reserveComp from '@/components/Reserve/ReserveComp.vue'

export default {
  components: { reserveComp }
}
</script>

<style>
.reserve {
  position: absolute;
  top: 0;
  width: 100%;
	margin-top: 10rem;
  /* font-family: 'GangwonEdu_OTFBoldA'; */
}
.reserve h1 {
  font-size: 50px;
  display: flex;
  justify-content: center;
  margin: 0 0 30px 0;
}
.reserve-comp {
  padding: 0 2rem 0 2rem;
}
</style>
<template>
  <div class="therapist-recom">
    <reserve-recommend-comp></reserve-recommend-comp>
  </div>
</template>

<script>
import ReserveRecommendComp from '../../components/reservecomp/ReserveRecommendComp.vue'

export default {
  components: {
    ReserveRecommendComp,  
  }
}
</script>

<style>
.therapist-recom {
  position: absolute;
  top: 0;
	margin-top: 8rem;
  width: 100%;
}
</style>
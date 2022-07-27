<template>
  <div>
    <div id="calanderDiv">
      <reserve-date-calander-comp></reserve-date-calander-comp>
    </div>

    <div id='dayDiv'>
      <reserve-day-calander-comp></reserve-day-calander-comp>
    </div>
  </div>
</template>

<script>
import ReserveDateCalanderComp from '../../components/reservecomp/ReserveDateCalanderComp.vue'
import ReserveDayCalanderComp from '../../components/reservecomp/ReserveDayCalanderComp.vue'

export default {
  components: {
    ReserveDateCalanderComp,
    ReserveDayCalanderComp,
  }
}
</script>

<style>
#calanderDiv {
  position: relative;
  right: 18%;
}

#dayDiv {
  position: absolute;
  width: 40%;
  top: 25%;
  left: 53%;
}
</style>
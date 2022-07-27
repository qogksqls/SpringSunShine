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
  position: absolute;
  top: 0;
  margin-top: 10rem;
  left: 5%;
}
.theme--light.v-application {
    background: #00000000;
    color: rgba(0, 0, 0, 0.87);
}
#dayDiv {
  position: absolute;
  top: 0;
  margin-top: 10rem;
  width: 40%;
  right: 5%;
}
</style>
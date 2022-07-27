<template>
  <div class="survey">
    <survey-comp></survey-comp>
  </div>
</template>

<script>
import SurveyComp from '@/components/survey/SurveyComp.vue'
export default {
  components: { SurveyComp }
}
</script>

<style>
.survey {
  position: absolute;
  top: 0;
  margin-top: 15rem;
  text-align: center;
  /* left: 0;
  transform: translate(-50%);
  right: 0;
  margin-left: auto;
  margin-right: auto; */
}
</style>
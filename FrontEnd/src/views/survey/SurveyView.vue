<template>
  <div class="survey">
    <survey-comp></survey-comp>
  </div>
</template>

<script>
import SurveyComp from '@/components/survey/SurveyComp.vue'

export default {
  components: { SurveyComp },
}
</script>

<style scoped>
.survey {
  position: absolute;
  top: 0;
  width: 100%;
	margin-top: 15rem;
  font-family: 'GangwonEdu_OTFBoldA';
}
</style>
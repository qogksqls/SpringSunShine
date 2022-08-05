<template>
  <div class="surveyresult">
    <h1>합계 : {{totalScore}}</h1>
    <hr>
    <div v-for="(ans, i) in $route.params.answer" :key="i" style="padding: 15px;">
      <h1>Q. {{ ans[0] }}</h1>
      <h2>{{ans[2]}}. {{ ans[1] }}</h2>
      <hr>
    </div>
  </div>
</template>

<script>
export default {
  name: "SurveyResultView",
  computed: {
    totalScore() {
      let total = 0
      for (let i = 0; i < this.$route.params.answer.length; i++) {
        total += Number(this.$route.params.answer[i][2])
      }
      return total
    }
  }
}
</script>

<style>
.surveyresult {
  position: absolute;
  top: 0;
  margin-top: 10rem;
}
</style>
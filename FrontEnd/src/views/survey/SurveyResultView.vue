<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class="px-5 px-lg-5 card-profile mt--300" no-body>
          <!--상담일지목록 시작-->
          <div class="wrap_content col col-lg-12 p-5">
            <div class="survey-card">
              <h3 class="col-lg-10">
                <div class="text-muted text-left mb-3">
                  <b>문진표 결과 : {{ totalScore }}</b>
                </div>
              </h3>
              <div class="surveyresult">
                <hr />
                <div
                  v-for="(ans, i) in $route.params.answer"
                  :key="i"
                  style="padding: 15px;"
                >
                  <h6>Q. {{ ans[0] }}</h6>
                  <h6>{{ ans[2] }}. {{ ans[1] }}</h6>
                  <hr />
                </div>
              </div>
            </div>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "SurveyResultView",
  computed: {
    totalScore() {
      let total = 0;
      for (let i = 0; i < this.$route.params.answer.length; i++) {
        total += Number(this.$route.params.answer[i][2]);
      }
      return total;
    },
  },
};
</script>

<style scoped>
/* .surveyresult {
  position: absolute;
  top: 0;
  margin-top: 10rem;
} */
</style>

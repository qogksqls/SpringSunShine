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
                  <b>문진표 결과 : {{ name }}</b>
                </div>
              </h3>
              <div class="surveyresult">
                <hr />
                <div
                  v-for="(ans, i) in answers"
                  :key="i"
                  style="padding: 15px;"
                >
                  <h5>Q. {{ ans['question'] }}</h5>
                  <h6>{{ ans['answer'] }}</h6>
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
import axios from 'axios';
export default {
  name: "SurveyResultView",
  data() {
    return {
      child_id: this.$route.params.childId,
      name: '',
      answers: []
    }
  },
  // computed: {
  //   totalScore() {
  //     let total = 0;
  //     for (let i = 0; i < this.$route.params.answer.length; i++) {
  //       total += Number(this.$route.params.answer[i][2]);
  //     }
  //     return total;
  //   },
  // },
  created() {
    console.log(this.$route.params.childId)
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/answer/getAnswer/${this.$route.params.childId}`,
      method: 'get',
    })
      .then(res => {
        // console.log(res.data)
        this.name = res.data['expertise']
        this.answers = res.data['answers']
      })
      .catch(err => {
        console.log(err.response)
      })
  }
};
</script>

<style scoped>
/* .surveyresult {
  position: absolute;
  top: 0;
  margin-top: 10rem;
} */
</style>

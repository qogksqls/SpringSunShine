<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class=" px-lg-5 card-profile mt--300" no-body>
          <!--상담사 추천 page start-->
          <div class="px-4 wrap_cont">
            <div v-if="this.$route.params.childId">
              <upper :childsCounselors="childsCounselors" />
            </div>

            <div class="mt-5 py-5 border-top text-center">
              <showTab :counselors="counselors" />
            </div>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>
<script>
import upper from "../../components/RecommendComp/CounselorUpper.vue";
import showTab from "../../components/RecommendComp/CounselorTab.vue";
import axios from "axios";

export default {
  name: "CounselorRecommend",
  components: { upper, showTab },
  data() {
    return {
      counselors: [],
      childsCounselors: [],
    };
  },
  created() {
    if (this.$route.params.childId) {
      console.log(this.$route.params.childId);
      axios({
        url: `https://i7a606.q.ssafy.io/service-api/therapist/recommend/child/${
          this.$route.params.childId
        }`,
        method: "get",
      })
        .then((res) => {
          console.log(res.data);
          this.childsCounselors = res.data;
        })
        .catch((err) => {
          console.log(err.response);
        });
    }
    console.log("전체 상담사");
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/therapist/recommend/all`,
      method: "get",
    })
      .then((res) => {
        console.log(res.data);
        this.counselors = res.data;
      })
      .catch((err) => {
        console.log(err.response);
      });
    
    axios({  // accessToken 재발급
      url: `https://i7a606.q.ssafy.io/service-api/auth/refresh/${this.$store.state.accounts.userid}`,
      method: 'get',
      headers: { Authorization: `Bearer ${this.$store.state.accounts.refreshToken}`}
    })
      .then(res => {
        console.log(res.data)
        this.$store.state.accounts.accessToken = res.data.accessToken
      })
      .catch(err => {
        console.log(err.response)
      })
  },
};
</script>
<style scoped>
.wrap_cont {
  margin-top: 100px;
}
.top_naming {
  margin-bottom: 100px;
}
</style>

<template>
  <div id="wrapChildContent" class="my-4">
    <div class="row col-sm-12 mt-5">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">이름</div>
        <div class="col-sm-8 text-muted">{{ childInfo['name'] }}</div>
      </div>
      <div class="ChildInfo col-sm-6 row">
        <div class="col-sm-4 text-muted">보호자이름</div>
        <div class="col-sm-8 text-muted">{{ parentInfo['name'] }}</div>
      </div>
    </div>
    <div class="row col-sm-12 mt-2 ">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">생년월일</div>
        <div class="col-sm-8 text-muted">{{ childInfo['birth'].slice(0, 10) }}</div>
      </div>
      <div class="ChildInfo col-sm-6 row">
        <div class="col-sm-4 text-muted">전화번호</div>
        <div class="col-sm-8 text-muted">{{ parentInfo['phone'] }}</div>
      </div>
    </div>
    <div class="row col-sm-12 mt-2 ">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">성별</div>
        <div class="col-sm-8 text-muted">{{ childInfo['gender'] }}</div>
      </div>
    </div>
    <div class="col-sm-12 text-right col">
      <base-button type="primary" class="col-sm-2" @click="moveSurveyResult">문진표</base-button>
      <!--학생일 시 student-->
      <router-link to="/webStudent" :child_id="childInfo['childId']" class="col-sm-2">
        <base-button type="primary">상담방</base-button>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      childInfo: this.$route.params,
      parentInfo: {}
    };
  },
  methods: {
    moveSurveyResult() {
      if (this.childInfo["surveyFlag"]) {
        this.$router.push({ name: "surveyresult", params: this.childInfo });
      } else {
        alert("문진표부터 작성해 주십시오.")
      }
    },
  },
  created() {
    console.log("상담일지 정보창")
    console.log(this.$route.params)
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/user/${this.$store.state.accounts.userid}`,
      method: 'get',
      headers: { Authorization: `Bearer ${this.$store.state.accounts.accessToken}`}
    })
      .then(res => {
        console.log(res.data.parentInfo)
        this.parentInfo = res.data.parentInfo
      })
      .catch(err => {
        console.log(err.response)
      })
  }
};
</script>

<style></style>

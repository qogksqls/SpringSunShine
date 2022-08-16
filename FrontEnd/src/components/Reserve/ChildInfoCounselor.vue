<template>
  <div id="wrapChildContent" class="my-4">
    <div class="row col-sm-12 mt-5">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">이름</div>
        <div class="col-sm-8 text-muted">{{ childInfo['childName'] }}</div>
      </div>
      <div class="ChildInfo col-sm-6 row">
        <div class="col-sm-4 text-muted">보호자이름</div>
        <div class="col-sm-8 text-muted">{{ childInfo['parentName'] }}</div>
      </div>
    </div>
    <div class="row col-sm-12 mt-2 ">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">생년월일</div>
        <div class="col-sm-8 text-muted">{{ childInfo2['birth'].slice(0, 10) }}</div>
      </div>
      <div class="ChildInfo col-sm-6 row">
        <div class="col-sm-4 text-muted">전화번호</div>
        <!-- <div class="col-sm-8 text-muted">{{ parentInfo['phone'] }}</div> -->
      </div>
    </div>
    <div class="row col-sm-12 mt-2 ">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">성별</div>
        <div class="col-sm-8 text-muted">{{ childInfo2['gender'] }}</div>
      </div>
    </div>
    <div class="col-sm-12 text-right col">
      <base-button type="primary" class="col-sm-2" @click="moveSurveyResult">문진표</base-button>
      <!--학생일 시 student-->
      <!-- <router-link to="/webStudent" class="col-sm-2" v-if="parent">
        <base-button type="primary">상담방</base-button></router-link
      > -->
      <!--상담사일 시 counselor-->
      <!-- <router-link to="/webCounselor" class="col-sm-2" v-else>
        <base-button type="primary">상담방</base-button></router-link
      > -->
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      childInfo: this.$route.params,
      childInfo2: {},
    };
  },
  methods: {
    moveSurveyResult() {
    //   if (this.childInfo["surveyFlag"]) {
    //     this.$router.push({ name: "surveyresult", params: this.childInfo });
    //   } else {
    //     alert("문진표가 작성되어 있지 않습니다.")
    //   }
    },
  },
  created() {
    console.log("아동정보쪽")
    console.log(this.$route.params)
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/child/reserv-therapist/${this.$route.params.childId}`,
      method: 'get'
    })
      .then(res => {
        console.log(res.data)
        this.childInfo2 = res.data
      })
      .catch(err => {
        console.log(err.response)
      })
  }
};
</script>

<style></style>

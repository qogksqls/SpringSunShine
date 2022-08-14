<template>
  <div id="wrapChildContent" class="my-4">
    <div class="row col-sm-12 mt-5">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">이름</div>
        <div class="col-sm-8 text-muted">"아이이름아이이름"</div>
      </div>
      <div class="ChildInfo col-sm-6 row">
        <div class="col-sm-4 text-muted">보호자이름</div>
        <div class="col-sm-8 text-muted">"아이이름"</div>
      </div>
    </div>
    <div class="row col-sm-12 mt-2 ">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">나이</div>
        <div class="col-sm-8 text-muted">"age"</div>
      </div>
      <div class="ChildInfo col-sm-6 row">
        <div class="col-sm-4 text-muted">전화번호</div>
        <div class="col-sm-8 text-muted">"010-0000-0000"</div>
      </div>
    </div>
    <div class="row col-sm-12 mt-2 ">
      <div class="ChildInfo col-sm-5 row">
        <div class="col-sm-4 text-muted">성별</div>
        <div class="col-sm-8 text-muted">"여"</div>
      </div>
    </div>
    <div class="col-sm-12 text-right col">
      <router-link to="/survey/result" class="col-sm-2">
        <base-button type="primary">문단표 보기</base-button></router-link
      >
      <!--학생일 시 student-->
      <router-link to="/webStudent" class="col-sm-2" v-if="parent">
        <base-button type="primary">상담방가기</base-button></router-link
      >
      <!--상담사일 시 counselor-->
      <router-link to="/webCounselor" class="col-sm-2" v-else>
        <base-button type="primary">상담방가기</base-button></router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style></style>

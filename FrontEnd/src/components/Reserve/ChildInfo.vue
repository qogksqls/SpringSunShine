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
      <div class="col-sm-5"></div>
      <router-link to="/survey/result" class="col-sm-2">
        <base-button type="primary">문단표 보기</base-button></router-link
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

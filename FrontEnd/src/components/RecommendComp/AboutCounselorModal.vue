<template>
  <div class="text-left">
    <h6 slot="header" class="modal-title" id="modal-title-default">
      <h4 class="display-4"><b id="counselor_name">"이름"</b>상담사</h4>
    </h6>
    <hr />
    <div class="col-lg-12 row">
      <div class="col-lg-3"><b>학력</b></div>
      <div class="col-lg-9 text-left">
        <p>건국대학교 어쩌구</p>
        <p>중앙대학교어쩌구중앙대학교어쩌구중앙대학교어쩌구</p>
      </div>

      <div class="col-lg-3"><b>경력</b></div>
      <div class="col-lg-9 text-left">
        <p>건국대학교 어쩌구</p>
        <p>중앙대학교어쩌구중앙대학교어쩌구중앙대학교어쩌구</p>
      </div>

      <div class="col-lg-3"><b>자격증</b></div>
      <div class="col-lg-9 text-left">
        <p>광진구꿀주먹</p>
        <p>싸피대통합</p>
      </div>
    </div>

    <template slot="footer">
      <base-button type="link" class="ml-auto" @click="modals.modal1 = false"
        >닫기
      </base-button>
      <base-button type="primary">예약하기</base-button>
    </template>
  </div>
</template>

<script>
export default {};
</script>

<style></style>

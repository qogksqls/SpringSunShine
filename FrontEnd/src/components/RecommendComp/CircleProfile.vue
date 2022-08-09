<template>
  <div class="m-3">
    <a href="#" @click="modals.modal1 = true">
      <img
        v-lazy="'img/theme/team-4-800x800.jpg'"
        class="rounded-circle img-fluid shadow-lg"
      />
      <div class="card_about text-center">
        <p>상담사 이름</p>
        <p>전문분야</p>
      </div>
    </a>
    <modal :show.sync="modals.modal1">
      <h6 slot="header" class="modal-title" id="modal-title-default">
        <h4 class="display-4"><b id="counselor_name">"이름"</b>상담사</h4>
      </h6>
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
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
export default {
  components: {
    Modal,
  },
  data() {
    return {
      modals: {
        modal1: false,
      },
    };
  },
};
</script>

<style scoped>
img {
  width: 180px;
}
.profile-page .card-profile .card-profile-image img {
  position: relative;
}
.card_about p {
  margin-bottom: 1px;
  color: #000;
}
</style>

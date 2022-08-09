<template>
  <div class="m-3">
    <a href="#" @click="modals.modal1 = true">
      <div class="card_about text-center">
        <p class="reserve_confirm">예약완료</p>
      </div>
    </a>
    <modal :show.sync="modals.modal1">
      <h6 slot="header" class="modal-title" id="modal-title-default">
        <h4 class="display-4">
            <h4>문진표가 작성되지 않았습니다.</h4>
            <h4>문진표 작성 후 예약해주세요</h4> 
        </h4>
      </h6>
        <base-button type="primary">작성하러 가기</base-button>
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
.card_about p {
  margin-bottom: 1px;
  color: #000;
}
</style>

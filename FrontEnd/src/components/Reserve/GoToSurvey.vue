<template>
  <div>
    <base-button type="default" class="childbutton" @click="modals.modal1 = true">
      상담사추천
    </base-button>
    <modal :show.sync="modals.modal1">
      <h6 slot="header" class="modal-title" id="modal-title-default">
        <h4 class="display-4">
            <h4>문진표가 작성되지 않았습니다.</h4>
            <h4>문진표 작성 후 예약해주세요</h4> 
        </h4>
      </h6>
      <router-link to="/survey" class="col-lg-6 px-0">
        <base-button type="primary" class="childbutton">문진표작성</base-button>
      </router-link>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import BaseButton from '../BaseButton.vue';
export default {
  components: {
    Modal,
  },
  data() {
    BaseButton
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

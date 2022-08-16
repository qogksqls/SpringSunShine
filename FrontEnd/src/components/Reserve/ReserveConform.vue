<template>
  <div class="mt-5 align-items-end">
    <!-- <a href="#/reserv-parent" @click="modals.modal1 = true"> -->
      <div class="card_about text-center">
        <base-button class="reserve_confirm" block type="primary" @click="reserve"
          >예약하기</base-button
        >
      </div>
    <!-- </a> -->
    <modal :show.sync="modals.modal1">
      <h6 slot="header" class="modal-title" id="modal-title-default">
        <h4 class="display-4">예약이 완료되었습니다.</h4>
      </h6>
      <div class="text-right">
        <base-button type="link" class="ml-auto" @click="modals.modalFlag = false"
          >닫기
        </base-button>
        <router-link to="/childReserveShow"
          ><base-button type="primary">상담일지 확인</base-button></router-link
        >
      </div>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import axios from 'axios';

export default {
  components: {
    Modal,
  },
  data() {
    return {
      modals: {
        modalFlag: false,
      },
    };
  },
  props: ['result'],
  methods: {
    reserve() {
      // console.log(this.$store.state.accounts.userid)
      // console.log(this.$route.params.childInfo.name)
      // console.log(this.$route.params.counselorInfo.thera_id)
      console.log(this.result)
      axios({
        url: 'https://i7a606.q.ssafy.io/service-api/reserv-parent',
        method: 'post',
        data: {
          "child_name": this.$route.params.childInfo.name,
          "parent_id": this.$store.state.accounts.userid,
          "thera_id": this.$route.params.counselorInfo.thera_id,
          "reserv_time": this.result
        }
      })
        .then(res => {
          console.log(res.data)
          this.modals.modalFlag = true
        })
        .catch(err => {
          console.log(err.response)
        })
    }
  }
};
</script>

<style>
@media (max-width: 767px) {
  .card {
    height: 100vh;
  }
  .profile-page {
    height: 100%;
  }
  .profile-page .card-profile {
    margin-top: -400px;
    height: 100vh;
  }
  .card {
    height: 100%;
  }
  .section-profile-cover {
    height: 150px !important;
  }
  .wrap_content {
    height: 100%;
    padding: 8px !important;
  }
}
</style>

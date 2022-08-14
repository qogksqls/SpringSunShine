<template>
  <div class="m-3">
    <a href="#" @click="modals.modal1 = true">
      <img
        v-lazy="'img/theme/team-4-800x800.jpg'"
        class="rounded-circle img-fluid shadow-lg"
      />
      <div class="card_about text-center">
        <h5>{{ counselor['name'] }} 상담사</h5>
        <div v-for="(expertise, i) in counselor['expertises']" :key="i">
          {{expertise['isKind']}}
        </div>
      </div>
    </a>
    <modal :show.sync="modals.modal1">
      <h6 slot="header" class="modal-title" id="modal-title-default">
        <h4 class="display-4"><b id="counselor_name">{{ counselor['name'] }} </b>상담사</h4>
      </h6>
      <div class="col-lg-12 row">
        <div class="col-lg-3"><b>전문분야</b></div>
        <div class="col-lg-10 text-left" v-for="(expertise, i) in counselor['expertises']" :key="i">
          <p>{{ expertise['isKind'] }}</p>
        </div>

        <div class="col-lg-3"><b>학력</b></div>
        <div class="col-lg-10 text-left" v-for="(aca, i) in counselor['academy']" :key="i">
          <p>{{ aca['name'] }} {{ aca['major']}} 졸업</p>
        </div>

        <div class="col-lg-3"><b>경력</b></div>
        <div class="col-lg-10 text-left" v-for="(career, i) in counselor['careers']" :key="i">
          <p>{{ career['name'] }} {{ career['role'] }}</p>
        </div>

        <div class="col-lg-3"><b>자격증</b></div>
        <div class="col-lg-10 text-left" v-for="(licen, i) in counselor['licence']" :key="i">
          <p>{{ licen['name'] }}</p>
        </div>
      </div>
      <template slot="footer">
        <base-button type="link" class="ml-auto" @click="modals.modal1 = false"
          >닫기
        </base-button>
        
        <base-button type="primary" @click="moveReservePage()">
          예약하기
        </base-button>
      </template>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
// import CounselorInfo from "./AboutCounselorModal.vue";

export default {
  name: 'CircleProfile',
  components: {
    Modal,
    // CounselorInfo,
  },
  data() {
    return {
      modals: {
        modal1: false,
      },
      childInfo: this.$route.params
    };
  },
  props: ['counselor'],
  methods: {
    moveReservePage() {
      this.$router.push({ name: "reserve", params: {
        "childInfo": this.childInfo,
        "counselorInfo": this.counselor,
        } 
      });
    },
  }
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

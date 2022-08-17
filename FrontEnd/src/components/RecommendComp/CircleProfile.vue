<template>
  <div class="m-3">
    <a href="#" @click="modals.modal1 = true" class="c_profile">
      <img
        :src="`data:image/png;base64,${counselor['profile_url']}`"
        class="rounded-circle img-fluid shadow-lg"
      >
      <div class="card_about text-center">
        <h5 class="pt-2">
          <b>{{ counselor["name"] }} 상담사</b>
        </h5>
        <div v-for="(expertise, i) in counselor['expertises']" :key="i">
          ▪{{ expertise["isKind"] }}
        </div>
      </div>
    </a>
    <modal :show.sync="modals.modal1">
      <h6 slot="header" class="modal-title" id="modal-title-default">
        <h4 class="display-4">
          <b id="counselor_name">{{ counselor["name"] }} </b>상담사
        </h4>
      </h6>
      <div class="text-left">
        <div class="m-0 align-self-end row justify-content-end col-md-12">
          <div class="col-md-3">
            <b>전문분야</b>
          </div>
          <div
            class="col-md-9 text-right"
            v-for="(expertise, i) in counselor['expertises']"
            :key="i"
          >
            <p>{{ expertise["isKind"] }}</p>
          </div>
        </div>

        <!--학력-->
        <div class="m-0 align-self-end row justify-content-end col-md-12">
          <div class="col-md-3"><b>학력</b></div>
          <div
            class="col-md-9 text-right"
            v-for="(aca, i) in counselor['academy']"
            :key="i"
          >
            <p>{{ aca["name"] }} {{ aca["major"] }} 졸업</p>
          </div>
        </div>

        <!--경력-->
        <div class="m-0 align-self-end row justify-content-end col-md-12">
          <div class="col-md-3"><b>경력</b></div>
          <div
            class="col-md-9 text-right"
            v-for="(career, i) in counselor['careers']"
            :key="i"
          >
            <p>{{ career["name"] }} {{ career["role"] }}</p>
          </div>
        </div>

        <!--자격증-->
        <div class="m-0 align-self-end row justify-content-end col-md-12">
          <div class="col-md-3"><b>자격증</b></div>
          <div
            class="col-md-9 text-right"
            v-for="(licen, i) in counselor['licence']"
            :key="i"
          >
            <p>{{ licen["name"] }}</p>
          </div>
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
  name: "CircleProfile",
  components: {
    Modal,
    // CounselorInfo,
  },
  data() {
    return {
      modals: {
        modal1: false,
      },
      childInfo: this.$route.params,
    };
  },
  props: ["counselor"],
  methods: {
    moveReservePage() {
      this.$router.push({
        name: "reserve",
        params: {
          childInfo: this.childInfo,
          counselorInfo: this.counselor,
        },
      });
    },
  },
};
</script>

<style scoped>
a {
  color: #989898;
}
a:hover {
  font-weight: bold;
}
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

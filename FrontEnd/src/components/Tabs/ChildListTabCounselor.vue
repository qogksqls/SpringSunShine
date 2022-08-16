<template>
  <div class="row justify-content-center">
    <div class="col-lg-12 mt-5 mt-lg-0">
      <tabs-counselor fill class="flex-column flex-md-row">
        <card shadow>
          <tab-pane-counselor title="예약내역">
            <list-list-counselor></list-list-counselor>
          </tab-pane-counselor>

          <!--상담사만 보는 곳-->
          <div v-if="this.$store.state.accounts.userid.slice(0, 1) === 't'">
            <tab-pane-counselor title="상담일지">
              <p class="description">
                <interview-counselor></interview-counselor>
              </p>
            </tab-pane-counselor>
          </div>
        </card>
      </tabs-counselor>
    </div>
  </div>
</template>
<script>
import Modal from "../Modal.vue";

import TabsCounselor from "@/components/Tabs/TabsCounselor.vue";
import TabPaneCounselor from "@/components/Tabs/TabPaneCounselor.vue";
import InterviewCounselor from "@/components/List/CounInterviewCounselor.vue";
import ListListCounselor from "@/components/List/RegisterListCounselor.vue";

import axios from "axios";

export default {
  name: "simple-pagination",
  components: {
    TabsCounselor,
    TabPaneCounselor,
    Modal,
    InterviewCounselor,
    ListListCounselor,
  },
  data() {
    return {};
  },
    created() {
  //     axios
  //       .get(`${this.$store.state.host}/consult`)
  //       .then((response) => {
  //         console.log(response);
  //         this.pageArray = response.data.contacts;
  //       })
  //       .catch((err) => {
  //         console.log(err);
  //       });
    },
};
</script>
<style></style>

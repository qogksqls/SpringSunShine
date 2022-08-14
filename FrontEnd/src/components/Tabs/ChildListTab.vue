<template>
  <div class="row justify-content-center">
    <div class="col-lg-12 mt-5 mt-lg-0">
      <tabs fill class="flex-column flex-md-row">
        <card shadow>
          <tab-pane title="예약내역">
            <list-list></list-list>
          </tab-pane>

          <!--상담사만 보는 곳-->
          <tab-pane title="상담일지">
            <p class="description">
              <Interview></Interview>
            </p>
          </tab-pane>
        </card>
      </tabs>
    </div>
  </div>
</template>
<script>
import Modal from "../Modal.vue";

import Tabs from "@/components/Tabs/Tabs.vue";
import TabPane from "@/components/Tabs/TabPane.vue";
import Interview from "@/components/List/CounInterviewComp.vue";
import ListList from "@/components/List/RegisterListComp.vue";

import axios from "axios";
export default {
  name: "simple-pagination",
  components: {
    Tabs,
    TabPane,
    Modal,
    Interview,
    ListList,
  },
  data() {
    return {};
  },
  //   created() {
  //     axios
  //       .get(`${this.$store.state.host}/consult`)
  //       .then((response) => {
  //         console.log(response);
  //         this.pageArray = response.data.contacts;
  //       })
  //       .catch((err) => {
  //         console.log(err);
  //       });
  //   },
};
</script>
<style></style>

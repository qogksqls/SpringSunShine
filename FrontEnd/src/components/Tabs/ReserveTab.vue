<template>
  <div class="row justify-content-center">
    <div class="col-lg-12 mt-5 mt-lg-0">
      <!-- Menu -->

      <tabs fill class="flex-column flex-md-row">
        <card shadow>
          <tab-pane title="예약내역">
            <table class="table table-hover text-center">
              <thead>
                <th :v-for="item in header" v-bind:key="header">{{ item }}</th>
              </thead>
              <tbody>
                <tr
                  :v-for="play in care"
                  v-bind:key="care"
                  @click="modals.modal2 = true"
                >
                  <td :v-for="item in play" v-bind:key="play">
                    {{ item }}
                  </td>
                </tr>
              </tbody>
            </table>
          </tab-pane>
          <!--모달 시작-->
          <modal :show.sync="modals.modal2">
            <h6 slot="header" class="modal-title" id="modal-title-default">
              <h4 class="display-4"><b id="date">"2022-08-09"</b>상담일지</h4>
            </h6>
            <div class="col-sm-12 row">
              <!--상담일지 저장공간-->
              <div>
                여기에 상담사가 상담일지 적은걸 보여줍니다~~~
              </div>
            </div>
            <template slot="footer">
              <base-button
                type="link"
                class="ml-auto"
                @click="modals.modal2 = false"
                >닫기
              </base-button>
            </template>
          </modal>

          <tab-pane title="상담일지">
            <p class="description"></p>
          </tab-pane>
        </card>
      </tabs>
    </div>
  </div>
</template>
<script>
import Tabs from "@/components/Tabs/Tabs.vue";
import TabPane from "@/components/Tabs/TabPane.vue";

import Modal from "@/components/Modal.vue";
export default {
  components: {
    Tabs,
    TabPane,
    Modal,
  },
  data() {
    return {
      header: ["NO.", "아동이름", "상담사", "날짜"],
      care: [
        [1, "공백이", "강선생", 20220101],
        [2, "두식이", "고선생", 20240506],
        [3, "석삼이", "박선생", 19960214],
      ],
      modals: {
        modal2: false,
      },
    };
  },
  /*  mounted() {
    this.fnGetList();
  },
  methods: {
    fnGetList() {
      this.$axios.get(`${this.$store.state.host}/consult`, {
        params: this.body.then((res) => {}),
      });
    },
  }, */
};
</script>

<style>
table {
  width: 100%;
  border-collapse: collapse;
}

table th {
  font-size: 1.2rem;
}

table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}

table tr:first-of-type {
  border-top: 2px solid #404040;
}

table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}

.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}

.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}

.btn-cover .page-count {
  padding: 0 1rem;
}
.takecare {
  background-clip: #ffdcb8;
}
th {
  height: 14px;
}
tr {
  cursor: pointer;
}
a {
  color: #000;
}
</style>

<template>
  <div id="wrap">
    <div class="wrap_content col col-sm-12 p-5">
      <h3><b>상담일지</b></h3>

      <info />
      <div class="col-md-12 takecare">
        <table class="table table-hover">
          <thead>
            <th v-for="item in header" v-bind:key="header">{{ item }}</th>
          </thead>
          <tbody>
            <tr v-for="play in care" v-bind:key="care">
              <td
                v-for="item in play"
                v-bind:key="play"
                @click="modals.modal1 = true"
              >
                {{ item }}
              </td>
            </tr>
          </tbody>
        </table>
        <!--모달 시작-->
        <modal :show.sync="modals.modal1">
          <h6 slot="header" class="modal-title" id="modal-title-default">
            <h4 class="display-4"><b id="date">"2022-08-09"</b>상담일지</h4>
          </h6>
          <div class="col-lg-12 row">
            <!--상담일지 저장공간-->
            <div>
              여기에 상담사가 상담일지 적은걸 보여줍니다~~~
            </div>
          </div>
          <template slot="footer">
            <base-button
              type="link"
              class="ml-auto"
              @click="modals.modal1 = false"
              >닫기
            </base-button>
          </template>
        </modal>
      </div>
    </div>
  </div>
</template>

<script>
import Modal from "@/components/Modal.vue";
import info from "./ChildInfo.vue";
export default {
  components: {
    Modal,
    info,
  },
  data() {
    return {
      modals: {
        modal1: false,
      },
      header: ["NO.", "아동이름", "상담사", "날짜"],
      care: [
        [1, "공백이", "강선생", 20220101],
        [2, "두식이", "고선생", 20240506],
        [3, "석삼이", "박선생", 19960214],
      ],
    };
  },
};
</script>

<style scoped>
.takecare {
  background-clip: #ffdcb8;
}
th {
  height: 14px;
}
tr {
  cursor: pointer;
}
</style>

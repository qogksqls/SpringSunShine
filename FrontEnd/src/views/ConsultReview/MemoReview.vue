<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default"></div>
    <div class="container pt-lg-md">
      <!--서두-->
      <h3>
        <div class="top_naming text-left mb-5">
          <b>{{ teacher.name }}님의 상담일지 작성</b>
        </div>
      </h3>
      <card
        type="secondary"
        shadow
        header-classes="bg-white pb-5"
        body-classes="px-lg-5 py-lg-5 row  justify-content-around my-lg-5"
        class="border-0"
      >
        <!--본문 시작-->
        <div class="col-md-5 memoReview mb-2 p-2">
          <h3><b>메모장</b></h3>
          <hr />
          <div class="memodata">
            <div style="display: flex; justify-content: space-between;">
              <p>{{datas.memo}}</p>
            </div>
          </div>
        </div>
        <div class="col-md-5 CounWrite mb-2 p-2">
          <h3><b>상담일지작성하기</b></h3>
          <textarea
            v-model = record
            class="form-control"
            id="exampleFormControlTextarea1"
            rows="7"
            placeholder="학부모에게 보여드릴 내용을 작성해주세요 ..."
          ></textarea>
        </div>

        <!--버튼-->
        <div class="col-sm-12 row justify-content-center mt-lg-5">
          <base-button @click="this.$router.push('/')" class="col-md-2 mt-2">취소</base-button>
          <!-- <base-button class="col-md-2 mt-2">수정하기</base-button> -->
          <base-button @click="saveRecord" class="col-md-2 mt-2">완료</base-button>
        </div>
      </card>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import axios from 'axios'
export default {
  data() {
    return {
      datas: this.$route.params,
      record: null,
    }
  },
  computed: {
    ...mapState({
      teacher: (state) => state.teacher.teacher,
    }),
  },
  methods: {
    saveRecord () {
      axios.put('https://i7a606.q.ssafy.io/service-api/consult/record', {
        consultNo: this.datas.consultNo,
        record: this.record
      })
      this.record = null
      this.$router.push('/')
    },
  },
}
</script>

<style scoped>
.section {
  height: 100%;
}
.memoReview {
  border-radius: 15px;
  background-color: #ffeebb;
}
.CounWrite {
  border-radius: 15px;
  background-color: #ffeebb;
}
</style>

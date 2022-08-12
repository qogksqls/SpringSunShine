<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class=" px-lg-5 card-profile mt--300" no-body>
          <!--상담사 상담할 아이들 일정 시작-->
          <div class="wrap_content col col-sm-12 p-5">
            <h3 class="mb-5">
              <b>"{{ teacher.name }}"님의 활동조회</b>
            </h3>
            나를 거쳐간/새로생긴 아이들 관리 페이지임
            <div class="col-sm-12 takecare row">
              <table class="table table-hover">
                <thead>
                  <th v-for="item in header" v-bind:key="header">{{ item }}</th>
                </thead>
                <tbody>
                  <tr v-for="play in care" v-bind:key="care">
                    <td v-for="item in play" v-bind:key="play">
                      {{ item }}
                    </td>

                    <div class="detail">
                      <router-link to="/childReserveShow">
                        <base-button class="rounded my-1"
                          >상담하기</base-button
                        ></router-link
                      >
                    </div>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!--상담사 상담할 아이들 일정 시작-->
        </card>
      </div>
    </section>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  components: {},
  data() {
    return {
      header: ["NO.", "예약시간", "부모이름", "아동이름", "입장버튼"],
      care: [
        [1, "20220810 오후7시", "강하다", "강후후"],
        [2, "20220810 오후3시", "강하다", "강후후"],
        [3, "20220610 오후7시", "기다", "공후후"],
      ],
    };
  },
  computed: {
    ...mapState({
      teacher: (state) => state.teacher.teacher,
    }),
  },
};
</script>

<style scoped>
th {
  text-align: center;
}
tr {
  text-align: center;
  border-bottom: 1px solid #dcdcdc;
}
.detail {
  margin-top: 2px;
}
</style>

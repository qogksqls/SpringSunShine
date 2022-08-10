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
            <h3 class="mb-5"><b>상담일정</b></h3>

            <div class="col-sm-12 takecare">
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
export default {
  components: {},
  data() {
    return {
      header: ["NO.", "날짜", "부모이름", "아동이름", "입장버튼"],
      care: [
        [1, 20220101, "강하다", "강후후"],
        [2, 20240506, "강하다", "강후후"],
        [3, 19960214, "강하다", "강후후"],
      ],
    };
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

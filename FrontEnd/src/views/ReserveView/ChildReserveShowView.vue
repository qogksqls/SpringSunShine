<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class=" px-lg-5 card-profile mt--300" no-body>
          <!--상담일지목록 시작-->
          <div class="wrap_content col col-lg-12 p-5">
            <h3><b>상담일지</b></h3>

            <div id="wrapChildContent" class="my-4">
              <div class="row col-lg-12 mt-5">
                <div class="ChildInfo col-lg-4 row">
                  <div class="col-lg-4 text-muted">이름</div>
                  <div class="col-lg-8 text-muted">"아이이름아이이름"</div>
                </div>
                <div class="ChildInfo col-lg-6 row">
                  <div class="col-lg-4 text-muted">보호자이름</div>
                  <div class="col-lg-8 text-muted">"아이이름"</div>
                </div>
              </div>
              <div class="row col-lg-12 mt-2 ">
                <div class="ChildInfo col-lg-4 row">
                  <div class="col-lg-4 text-muted">나이</div>
                  <div class="col-lg-8 text-muted">"age"</div>
                </div>
                <div class="ChildInfo col-lg-6 row">
                  <div class="col-lg-4 text-muted">전화번호</div>
                  <div class="col-lg-8 text-muted">"010-0000-0000"</div>
                </div>
              </div>
              <div class="row col-lg-12 mt-2 ">
                <div class="ChildInfo col-lg-4 row">
                  <div class="col-lg-4 text-muted">성별</div>
                  <div class="col-lg-8 text-muted">"여"</div>
                </div>
                <div class="col-lg-6"></div>
                <router-link to="/survey/result" class="col-lg-2">
                  <base-button type="primary"
                    >문단표 보기</base-button
                  ></router-link
                >
              </div>
            </div>

            <div class="col-md-12 takecare">
              <table class="table table-hover">
                <thead>
                  <th v-for="item in header" v-bind:key="header">{{ item }}</th>
                </thead>
                <tbody>
                  <tr v-for="play in care" v-bind:key="care">
                    <td v-for="item in play" v-bind:key="play">{{ item }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <!--상담일지목록 end-->
        </card>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
</style>

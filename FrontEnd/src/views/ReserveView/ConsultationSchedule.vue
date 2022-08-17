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
              <b>{{ theraInfo["name"] }} 상담사의 스케쥴</b>
            </h3>
            <div class="col-sm-12 takecare row">
              <table class="table table-hover">
                <thead>
                  <th v-for="(item, i) in header" :key="i">{{ item }}</th>
                </thead>
                <tbody>
                  <tr v-for="(schedule, i) in schedules" :key="i">
                    <td>{{ i+1 }}</td>
                    <td>{{ schedule["reservTime"].slice(0, 4) }}년
                      {{ schedule["reservTime"].slice(5, 7) }}월
                      {{ schedule["reservTime"].slice(8, 10) }}일
                      {{ Number(schedule["reservTime"].slice(11, 13)) + 9 }}시
                    </td>
                    <td>{{ schedule["parentName"] }}</td>
                    <td>{{ schedule["childName"] }}</td>

                    <div class="detail">
                      <base-button class="rounded my-1" @click="moveChildInfo(schedule)">
                        상담하기
                      </base-button>
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
import axios from 'axios'

var today = new Date();
var year = today.getFullYear();
var month = ("0" + (today.getMonth() + 1)).slice(-2);
var day = ("0" + today.getDate()).slice(-2);
var dateString = year + "-" + month + "-" + day;

export default {
  data() {
    return {
      header: ["NO.", "예약시간", "부모이름", "아동이름", "입장버튼"],
      theraInfo: {},
      schedules: []
    };
  },
  methods: {
    moveChildInfo(schedule) {
      this.$router.push({ name: 'childReserveShowCounselor', params: schedule })
    }
  },
  created() {
    console.log(this.$store.state.accounts.accessToken)
    axios.get(`https://i7a606.q.ssafy.io/service-api/user/${this.$store.state.accounts.userid}`, {
      headers : {
        Authorization: `Bearer ${ this.$store.state.accounts.accessToken }`
      },
    })
      .then((res) => {
        console.log(res.data);
        this.theraInfo = res.data.theraInfo
      })
      .catch((err) => {
        console.log(err.response);
      });
    this.$axios({
      url: `https://i7a606.q.ssafy.io/service-api/reserv-therapist/${this.$store.state.accounts.userid}`,  // 예약조회
      method: 'get',
    })
      .then((res) => {
        console.log(res.data);
        this.schedules = res.data
      })
      .catch((err) => {
        console.log(err.response);
      });
  }
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

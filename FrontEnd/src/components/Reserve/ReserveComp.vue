<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class="py-5 px-lg-5 card-profile mt--300" no-body>
          <div>
            <!--상담사 스케쥴 -->
            <h3>
              <div class="top_naming text-left mb-5">
                <b>{{ counselorInfo.name }} 상담사의 스케줄</b>
              </div>
            </h3>
            <!--본문 시작-->
            <div id="wrap_content" class="row col-12 justify-content-around">
              <!--날짜 선택-->
              <div class="calendar123 col-md-5 row">
                <base-input class="col-md-12 p-0" disabled>
                  <flat-picker
                    slot-scope="{ focus, blur }"
                    @on-open="focus"
                    @on-close="blur"
                    :config="{ allowInput: true, inline: true, locale: ko }"
                    class="datepicker col-md-12"
                    v-model="date"
                  >
                  </flat-picker>`4`
                </base-input>
              </div>
              <!--시간 선택-->

              <div class="schedule123  col-md-5">
                <div class="upper_cont row">
                  <h3 class="col-md-9">
                    <div v-if="hour === '몇'">
                      {{ date.slice(5, 7) }}월 {{ date.slice(8, 10) }}일
                      {{ hour }} 시
                    </div>
                    <div v-else>
                      {{ date.slice(5, 7) }}월 {{ date.slice(8, 10) }}일
                      {{ hour + 9 }} 시
                    </div>
                  </h3>
                  <base-button
                    @click="dateSelected"
                    class="col-md-3"
                    type="primary"
                    >조회</base-button
                  >
                </div>
                <hr />
                <div class="schdule_buttons my-3">
                  <div class="wrap_btn row col-12 px-1">
                    <div
                      v-for="(time, i) in possibleTimes"
                      :key="i"
                      class="col-3"
                      outline
                      type="default"
                    >
                      <div v-if="time[1] === 1">
                        <base-button
                          outline
                          type="primary"
                          class="possible_buttons"
                          @click="possibleButton(time[0])"
                        >
                          {{ time[0] }}:00
                        </base-button>
                      </div>
                      <div v-else>
                        <base-button
                          disabled
                          type="secondary"
                          class="impossible_buttons"
                          @click="impossibleButton"
                        >
                          {{ time[0] }}:00
                        </base-button>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="mt-3">
                  <div v-if="hour !== '몇'">
                    <ReserveConfirm :result="result" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>

<script>
import ReserveConfirm from "./ReserveConform.vue";
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";
//import "flatpickr/dist/themes/confetti.css";
import { Korean } from "flatpickr/dist/l10n/ko.js";

import axios from 'axios'

var today = new Date();
var year = today.getFullYear();
var month = ("0" + (today.getMonth() + 1)).slice(-2);
var day = ("0" + today.getDate()).slice(-2);
var dateString = year + "-" + month + "-" + day;

export default {
  components: {
    flatPicker,
    ReserveConfirm,
  },
  data() {
    return {
      childInfo: this.$route.params.childInfo,
      counselorInfo: this.$route.params.counselorInfo,

      reserveTimes: {},
      date: dateString,
      times: [9, 10, 11, 13, 14, 15, 16, 17],
      possibleTimes: [],
      impossibleTimes: [],
      hour: "몇",
      
      result: ''
    };
  },
  created() {
    console.log(this.$route.params)
    console.log('예약하기 페이지')
    console.log(this.counselorInfo.thera_id)
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/reserv-therapist/${this.counselorInfo.thera_id}`,
      method: 'get'
    })
      .then(res => {
        console.log(res.data)
        for (let i = 0; i < res.data.length; i++) {
          const date = res.data[i]["reservTime"].slice(0, 10)
          const hour = Number(res.data[i]["reservTime"].slice(11, 13)) + 9
          if (date in this.reserveTimes) {
            this.reserveTimes[date].push(hour)
          } else {
            this.reserveTimes[date] = [hour]
          }
        }
      })
      .catch(err => {
        console.log(err.response)
      })
  },
  methods: {
    dateSelected() {
      console.log(this.date);
      this.possibleTimes = []
      if (this.date in this.reserveTimes) {
        for (const time of this.times) {
          let check = 1;
          for (let i = 0; i < this.reserveTimes[this.date].length; i++) {
            if (this.reserveTimes[this.date][i] === time) {
              check = 0;
              break;
            }
          }
          if (check) {
            this.possibleTimes.push([time, 1]);
          } else {
            this.possibleTimes.push([time, 0]);
          }
        }
      } else {
        this.possibleTimes = [[9, 1], [10, 1], [11, 1], [13, 1], [14, 1], [15, 1], [16, 1], [17, 1]]
      }
      this.hour = "몇";
    },
    impossibleButton() {
      alert("이미 예약된 시간입니다. 다른 시간을 선택해 주세요.");
    },
    possibleButton(t) {
      // "2022-09-04T04:30:00.000+00:00"
      this.hour = t-9;
      this.result = `${this.date}T0${this.hour}:00:00`
    },
  },
};
</script>

<style scoped>
.possible_buttons {
  border-radius: 30px;
  margin: 10px;
  font-weight: bold;
  float: left;
  box-shadow: 3px 3px 3px black;
  transition-duration: 0.3s;
  cursor: pointer;
}
.impossible_buttons {
  background-color: rgba(151, 142, 125, 0.436);
  border-radius: 30px;
  margin: 10px;
  font-weight: bold;
  float: left;
  box-shadow: 3px 3px 3px black;
  transition-duration: 0.3s;
  color: gray;
}
.schdule_buttons button:active {
  box-shadow: none;
  cursor: pointer;
  box-shadow: 3px 3px 3px black;
}
.schdule_buttons button:hover {
  box-shadow: none;
  cursor: pointer;
  box-shadow: 3px 3px 3px black;
}
.schdule_buttons button:focus {
  background-color: brown;
  color: #fff;
  box-shadow: 3px 3px 3px black;
}

input {
  font-size: 1.2rem;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 15px;
  border: 1px solid #dcdcdc;
}
@media (max-width: 1023px) {
}
@media (max-width: 767px) {
  .profile-page .card-profile {
    height: 100%;
  }
  #wrap_content {
    padding: 0;
    margin: 0;
  }
  .calendar123 {
    padding: 0;
  }
  .schedule123 {
    padding: 0;
  }
  .upper_cont {
    padding: 0 15px;
  }
  .schdule_buttons {
    overflow-wrap: normal;
  }
  .wrap_btn {
    margin: 0;
    padding: 0;
  }
}
</style>

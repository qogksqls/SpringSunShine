<template>
  <div >
    <h1>{{ teacher.name }} 교사님의 스케줄</h1>
    <div class="cs">
      <div class="calendar123">
        <base-input addon-left-icon="ni ni-calendar-grid-58">
          <flat-picker slot-scope="{focus, blur}"
            @on-open="focus"
            @on-close="blur"
            :config="{allowInput: true}"
            class="form-control datepicker"
            v-model="date">
          </flat-picker>
        </base-input>
      </div>
      <div class="schedule123">
        <div id="schdule_card">
          <h1 style="margin: 10px 0 10px 30px;">
            {{ date.slice(5, 7) }}월 {{ date.slice(8, 10) }}일 {{ hour }} 시
            <h3><button @click="dateSelected">조회</button></h3>
          </h1>
          <hr>
          <div class="schdule_buttons">
            <div class="container">
              <div class="col-sm">
                <div v-for="(time, i) in possibleTimes" :key="i">
                  <div v-if="time[1] === 1">
                    <button class="possible_buttons" @click="possibleButton(time[0])">{{ time[0] }}:00</button>
                  </div>
                  <div v-else>
                    <button class="impossible_buttons" @click="impossibleButton">{{ time[0] }}:00</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div v-if="문진표유무">
            <div v-if="hour !== '몇'">
              <ReserveConfirm />
            </div>
          </div>
          <div v-else>
            <GoToSurvey />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

import ReserveConfirm from './ReserveConform.vue'
import GoToSurvey from './GoToSurvey.vue'
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";


var today = new Date();
var year = today.getFullYear();
var month = ('0' + (today.getMonth() + 1)).slice(-2);
var day = ('0' + today.getDate()).slice(-2);
var dateString = year + '-' + month  + '-' + day;

export default {
  components: {
    flatPicker, ReserveConfirm, GoToSurvey
  },
  data() {
    return {
      date: dateString,
      now: [],
      times: [9, 10, 11, 13, 14, 15, 16, 17],
      possibleTimes: [],
      impossibleTimes: [],

      hour: '몇',
      value: dateString,
      context: null,
      showDecadeNav: false,
      hideHeader: true,

      isCompleteSurvey: '',
      문진표유무: ''
    }
  },
  computed: {
    ...mapState({
      teacher: state => state.teacher.teacher,
      children: state => state.children.children
    }),
    asdf() {
      return this.possibleTimes
    }
  },
  created() {
    this.문진표유무 = this.children[1]['문진표']
  },
  methods: {
    dateSelected() {
      console.log(this.date)
      let impossibleTimes = []
      this.value = this.date
      this.month = this.value.slice(5, 7)
      this.day = this.value.slice(8, 10)
      if (this.teacher.reserveTime[this.value]) {
        impossibleTimes = this.teacher.reserveTime[this.value]
      }
      this.possibleTimes = []
      for (const time of this.times) {
        let check = 1
        for (let i = 0; i < impossibleTimes.length; i++) {
          if (impossibleTimes[i] === time) {
            check = 0
            break
          }
        }
        if (check) {
          this.possibleTimes.push([time, 1])
        } else {
          this.possibleTimes.push([time, 0])
        }
      }
      this.hour = '몇'
      return this.possibleTimes
    },
    impossibleButton() {
      alert('이미 예약된 시간입니다. 다른 시간을 선택해 주세요.')
    },
    possibleButton(t) {
      this.hour = t
    },
  },
}
</script>

<style>
.cs {
  display: flex;
  justify-content: space-evenly;
}
.calendar123 {
  /* border: solid 1px; */
  width: 45%;
  height: 40rem;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.b_calendar {
  width: 15rem;
}
/* .calendar_toolbar {
  display: flex;
  justify-content: space-between;
}
.toolbar-title {
  display: flex;
  align-items: center;
} */
.schedule123 {
  /* border: solid; */
  width: 45%;
  height: 40rem;
  display: flex;
}
#schdule_card {
  width: 100%;
  background-color: blanchedalmond;
}
.possible_buttons {
  background-color: rgb(255, 202, 89);
  width: 10rem;
  height: 5rem;
  border-radius: 30px;
  margin: 10px;
  font-size: 40px;
  font-weight: bold;
  float: left;
  box-shadow: 3px 3px 3px black;
  transition-duration: 0.3s;
}
/* .possible_buttons_click {
  background-color: rgb(255, 238, 201);
  width: 10rem;
  height: 5rem;
  border-radius: 30px;
  margin: 10px;
  font-size: 40px;
  font-weight: bold;
  float: left;
} */
.impossible_buttons {
  background-color: rgba(151, 142, 125, 0.436);
  width: 10rem;
  height: 5rem;
  border-radius: 30px;
  margin: 10px;
  font-size: 40px;
  font-weight: bold;
  float: left;
  box-shadow: 3px 3px 3px black;
  transition-duration: 0.3s;
  color: gray;
}
.schdule_buttons button:active {
  box-shadow: none;
}
#app {
  width: 100%;
}
.reserve_confirm {
  position: absolute;
  bottom: 10px;
  right: 120px;
  background-color: rgb(252, 176, 157);
  width: 10rem;
  height: 3rem;
  border-radius: 30px;
  /* margin: 10px; */
  font-size: 20px;
  font-weight: bold;
  float: left;
  /* box-shadow: 3px 3px 3px black; */
  transition-duration: 0.3s;
}
</style>
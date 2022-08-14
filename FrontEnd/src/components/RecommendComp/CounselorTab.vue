<template>
  <div class="container">
    <div class="tab-box nav justify-content-start">
      <ul class="row col-lg-12 tab-btn-list m-0 p-0">
        <li
          class="col-lg-2 nav-item tabstyle"
          v-for="(tab, index) in tabList"
          :key="index"
          :class="{ active: currentTab === index }"
        >
          <a href="#" class="nav-link" @click.prevent="currentTab = index">{{
            tab
          }}</a>
        </li>
      </ul>
    </div>
    <div v-show="currentTab == 0" class="tab-cont col-lg-12">
      <div class="row justify-content-center mt-4 text-center">
        <div v-for="(counselor, i) in counselors" :key="i">
          <div v-for="(expertise, i) in counselor['expertises']" :key="i">
            <div v-if="expertise['expertiseNo'] == 1">
              {{counselor}}
              <!-- <circleProfile :counselor="counselor" /> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-show="currentTab == 1" class="tab-cont col-lg-12">
      <div class="row justify-content-center mt-4 text-center">
        <div v-for="(counselor, i) in counselors" :key="i">
          <div v-for="(expertise, i) in counselor['expertises']" :key="i">
            <div v-if="expertise['expertiseNo'] == 2">
              {{counselor}}
              <!-- <circleProfile :counselor="counselor" /> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-show="currentTab == 2" class="tab-cont col-lg-12">
      <div class="row justify-content-center mt-4 text-center">
        <div v-for="(counselor, i) in counselors" :key="i">
          <div v-for="(expertise, i) in counselor['expertises']" :key="i">
            <div v-if="expertise['expertiseNo'] == 3">
              {{counselor}}
              <!-- <circleProfile :counselor="counselor" /> -->
            </div>
          </div>
        </div>
      </div></div>
    <div v-show="currentTab == 3" class="tab-cont col-lg-12">
      <div class="row justify-content-center mt-4 text-center">
        <div v-for="(counselor, i) in counselors" :key="i">
          <div v-for="(expertise, i) in counselor['expertises']" :key="i">
            <div v-if="expertise['expertiseNo'] == 4">
              <!-- <circleProfile :counselor="counselor" /> -->
            </div>
          </div>
        </div>
      </div></div>
    <div v-show="currentTab == 4" class="tab-cont col-lg-12">
      <div class="row justify-content-center mt-4 text-center">
        <div v-for="(counselor, i) in counselors" :key="i">
          <div v-for="(expertise, i) in counselor['expertises']" :key="i">
            <div v-if="expertise['expertiseNo'] == 5">
              {{counselor}}
              <!-- <circleProfile :counselor="counselor" /> -->
            </div>
          </div>
        </div>
      </div></div>
  </div>
</template>

<script>
import circleProfile from "./CircleProfile.vue";

export default {
  components: { circleProfile },
  data() {
    return {
      currentTab: 0,
      tabList: [
        "자폐증",
        "아스퍼거 증후군",
        "전반적 발달 장애",
        "레트 증후군",
        "아동기 붕괴성 장애",
      ],
    };
  },
  props: [
    'counselors'
  ],
  created() {
    console.log()
  }
};
</script>

<style scoped>
.tabstyle {
  padding: 10px 5px;
  border: 1px solid #dcdcdc;
}
li {
  display: inline-block;
}
.tab-cont {
  margin-bottom: 30px;
  padding: 20px;
  text-align: left;
  border: 1px solid #dcdcdc;
  float: left;
  padding: 10px;
}
</style>

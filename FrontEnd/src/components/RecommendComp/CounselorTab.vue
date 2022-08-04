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
        <circleProfile />
        <circleProfile />
        <circleProfile />
        <circleProfile />
        <circleProfile />
        <circleProfile />
        <circleProfile />
      </div>
    </div>
    <div v-show="currentTab == 1" class="tab-cont col-lg-12">
      1111111
    </div>
    <div v-show="currentTab == 2" class="tab-cont col-lg-12">333333333333</div>
    <div v-show="currentTab == 3" class="tab-cont col-lg-12">4444ㅋ44444</div>
    <div v-show="currentTab == 4" class="tab-cont col-lg-12">4444ㅋ44444</div>
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
        "자폐장애",
        "아스퍼거증후군",
        "렛트증후군",
        "소아기붕괴성장애",
        "기타발달장애",
      ],
    };
  },
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

<template>
  <div id="wrap">
    <div class="wrap_content col col-sm-12 p-5">
      <h3><b>상담일지</b></h3>

      <!--상담일지에 들어갈 아이 이름 컴포넌트-->
      <info />
      <!--Tab을 통해 예약내역/ 상담일지 보여줄 계획-->
      <listTab />
    </div>
  </div>
</template>

<script>
import listTab from "../Tabs/ChildListTab.vue";
import info from "./ChildInfo.vue";
export default {
  components: {
    info,
    listTab,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>

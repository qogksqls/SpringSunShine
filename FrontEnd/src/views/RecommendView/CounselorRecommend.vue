<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class=" px-lg-5 card-profile mt--300" no-body>
          <!--상담사 추천 page start-->
          <div class="px-4 wrap_cont">
            <upper />

            <div class="mt-5 py-5 border-top text-center">
              <showTab />
            </div>
            <circle />
          </div>
        </card>
      </div>
    </section>
  </div>
</template>
<script>
import upper from "../../components/RecommendComp/CounselorUpper.vue";
import showTab from "../../components/RecommendComp/CounselorTab.vue";
import circle from "../../components/RecommendComp/CircleProfile.vue";
export default {
  components: { upper, showTab, circle },
};
</script>
<style scoped>
.wrap_cont {
  margin-top: 100px;
}
.top_naming {
  margin-bottom: 100px;
}
</style>

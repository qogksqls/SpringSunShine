<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class=" px-lg-5 card-profile mt--300" no-body>
          <!--상담일지목록 시작-->
          <div>
            <childtablecounselor />
          </div>
          <!--상담일지목록 end-->
        </card>
      </div>
    </section>
  </div>
</template>

<script>
import childtablecounselor from "../../components/Reserve/ChildReserveCounselor.vue";
export default {
  components: {
    childtablecounselor,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>

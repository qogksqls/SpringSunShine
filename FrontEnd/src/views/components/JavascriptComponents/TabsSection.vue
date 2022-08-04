<template>
  <div class="row justify-content-center">
    <div class="col-lg-12 mt-5 mt-lg-0">
      <!-- Menu -->
      <div class="mb-3">
        <small class="text-uppercase font-weight-bold">With text</small>
      </div>
      <tabs fill class="flex-column flex-md-row">
        <card shadow>
          <tab-pane title="자폐장애">
            <p class="description text-center row col-lg-12">
              <circleProfile />
              <circleProfile />
              <circleProfile />
              <circleProfile />
              <circleProfile />
              <circleProfile />
              <circleProfile />
            </p>
          </tab-pane>

          <tab-pane title="아스퍼거증후군">
            <p class="description">
              Cosby sweater eu banh mi, qui irure terry richardson ex squid.
              Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan
              american apparel, butcher voluptate nisi qui.
            </p>
          </tab-pane>

          <tab-pane title="렛트증후군">
            <p class="description">
              Raw denim you probably haven't heard of them jean shorts Austin.
              Nesciunt tofu stumptown aliqua, retro synth master cleanse.
              Mustache cliche tempor, williamsburg carles vegan helvetica.
              Reprehenderit butcher retro keffiyeh dreamcatcher synth.
            </p>
          </tab-pane>
          <tab-pane title="소아기붕괴성장애">
            <p class="description">
              Raw denim you probably haven't heard of them jean shorts Austin.
              Nesciunt tofu stumptown aliqua, retro synth master cleanse.
              Mustache cliche tempor, williamsburg carles vegan helvetica.
              Reprehenderit butcher retro keffiyeh dreamcatcher synth.
            </p>
          </tab-pane>
          <tab-pane title="기타발달장애">
            <p class="description">
              Raw denim you probably haven't heard of them jean shorts Austin.
              Nesciunt tofu stumptown aliqua, retro synth master cleanse.
              Mustache cliche tempor, williamsburg carles vegan helvetica.
              Reprehenderit butcher retro keffiyeh dreamcatcher synth.
            </p>
          </tab-pane>
        </card>
      </tabs>
    </div>
  </div>
</template>
<script>
import Tabs from "@/components/Tabs/Tabs.vue";
import TabPane from "@/components/Tabs/TabPane.vue";
import circleProfile from "../../../components/RecommendComp/CircleProfile.vue";
export default {
  components: {
    Tabs,
    TabPane,
    circleProfile,
  },
};
</script>
<style></style>

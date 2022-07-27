<template>
  <v-app>
    <v-row>
      <v-col>
        <v-sheet height="400">
          <v-calendar
            color="primary"
            type="day"
          >
            <template v-slot:day-header="{ present }">
              <template
                v-if="present"
              >
              </template>
            </template>

            <template v-slot:interval="{ hour }">
              <div
                class="text-center"
              >
                {{ hour }} o'clock
              </div>
            </template>
          </v-calendar>
        </v-sheet>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
  export default {
    data: () => ({}),
  }
</script>

<style scoped>

</style>
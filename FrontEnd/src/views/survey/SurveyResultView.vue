<template>
  <div class="surveyresult">
    <h1>결과창</h1>
    <h2>name: {{ $route.params}}</h2>

    <h2>{{ name }}</h2>
    <h2>{{ name }}</h2>
  </div>
</template>

<script>
export default {
  name: "SurveyResultView",
  props: {
    name: {
      name: {
        type: String,
        default: ''
      },
      age: {
        type: Number,
        default: 0
      }
    }
  },
  created() {
    console.log(this.$route)
  }
  // methods: {
  //   data() {
  //     return {
  //       arr: [],
  //       total: 0
  //     }
  //   },
  //   setInput(arr) {
  //     this.arr = arr
  //     var lodash = require('lodash');
  //     let total = []
  //     console.log(total)
  //     for (let i = 0; i < this.arr.length; i++) {
  //       total.push(Number(this.arr[i][1]))
  //     }
  //     const result = lodash.sum(total)
  //     console.log(lodash.sum(total))
  //     return result
  //   }
  // }
}
</script>

<style>
.surveyresult {
  position: absolute;
  top: 0;
  margin-top: 10rem;
}
</style>
<template>
  <div>
    <div v-for="question in data" :key="question.pk">
      <h1>Q. {{ question.title }}</h1>
      <h3>
        <div v-for="answer in question.object_answers" :key="answer.pk">
          {{ answer[0] }} - {{ answer[1] }}
        </div>
      </h3>
    </div>
  </div>
</template>

<script>
import survey from './survey.json'

const data = survey

export default {
  data() {
    return {
      data
    }
  }
}
</script>

<style>
</style>
<template>
  <div class="survey-card"> 
    <div class="survey-title">
      <h1>문진표</h1>
    </div>
    <div class="question-card" v-for="(question, i) in data" :key="i">
      <div class="question">
        <h2>Q. {{ question.title }}</h2>
      </div>
      <div class="object_answer">
        <div v-for="(object_answer, j) in question.object_answers" :key="j">
          <h2>
            <input type="radio" id="radio" v-model="answer[i]" :value="object_answer"> 
            <label for="radio">  {{ object_answer[1] }}</label>
          </h2>
          <h2>{{ object_answer[0] }}</h2>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import survey from './survey.json'

const data = survey
const answer = []
for (let index = 0; index < survey.length; index++) {
  answer.push([0, 0, 0, 0, 0])
}

export default {
  data() {
    return {
      data,
      answer,
    }
  },
}
</script>

<style>
.survey-card {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.survey-title {
  display: flex;
  justify-content: center;
  font-size: 2.5rem;
  font-weight: bold;
  margin-bottom: 2rem;
}
.question-card {
	border-radius: 15px;
}
.question {
  background-color: rgb(255, 189, 83);
  padding: 2rem 0 2rem 2rem;
	border-radius: 10px;
  width: 80rem;
}
.object_answer {
  display: flex;
  justify-content: space-around;
  background-color: rgb(255, 233, 172);
  padding: 1rem 0 1rem 0;
	border-radius: 10px;
}
</style>
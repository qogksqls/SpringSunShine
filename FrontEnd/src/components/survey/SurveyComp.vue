<template>
  <div>
    <div class="survey-card"> 
      <div class="survey-title">
        <h1>문진표</h1>
      </div>
      <div class="question-card" v-for="(question, i) in data" :key="i">
        <div class="question">
          <h2>Q{{ question.number}}. {{ question.title }}</h2>
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
    <li @click="clickParams">
      params
    </li>
    <router-link 
    :to="{
      name: 'surveyresult',
      params: answer
    }">
      <h2 class="submit">Submit</h2>
    </router-link>
  </div>
</template>

<script>
import survey from './survey.json'

const data = survey
const answer = []
for (let index = 0; index < survey.length; index++) {
  answer.push([])
}

export default {
  data() {
    return {
      data,
      answer,
    }
  },
  methods: {
    onSubmit() {
      alert("문진표가 제출되었습니다.")
      this.$router.push(answer)
    },
    clickParams() {
      this.$router.push({name: 'surveyresult', params: {name: 'dog', age: 4}})
    }
  }
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
.submit {
  background-color: rgb(255, 111, 0);
	border-radius: 10px;
  font-size: 2rem;
  margin-top: 1rem;
  position: absolute;
  left: 85%;
}
</style>
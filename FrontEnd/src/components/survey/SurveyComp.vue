<template>
  <div>
    <div class="survey_title">
      문진표
    </div>
    <div class="survey-card"> 
      <div class="question-card" v-for="(question, i) in data" :key="i">
        <div class="question">
          <h2>Q{{ question.number}}. {{ question.title }}</h2>
        </div>
        <div>
          <div v-for="(object_answer, j) in question.object_answers" :key="j">
            <div class="object_answer">
              <h2>
                <input type="radio" id="radio" v-model="answer[i]" :value="[question.title, object_answer[0], object_answer[1]]"> 
                <span>___{{ object_answer[1] }}___</span>
                <!-- <label for="radio">  {{ object_answer[1] }}</label> -->
              </h2>
              <h2>{{ object_answer[0] }}</h2>
            </div>
          </div>
        </div>
      </div>
      <button @click="onSubmit">
        <h2 class="submit">Submit</h2>
      </button>
    </div>
  </div>
</template>

<script>
import survey from './survey.json'

const data = survey
const answer = []
// for (let index = 0; index < survey.length; index++) {
//   answer.push([])
// }

export default {
  data() {
    return {
      data,
      answer,
    }
  },
  methods: {
    onSubmit() {
      if (answer.length === 23) {
        alert("문진표가 제출되었습니다.")
        this.$router.push({name: 'surveyresult', params: { answer }})
      } else {
        alert("작성 안된 항목이 있습니다.")
      }
    }
  }
}
</script>

<style >
.survey-card {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.survey_title {
  display: flex;
  justify-content: center;
  font-size: 5rem;
  font-weight: bold;
  margin-bottom: 2rem;
}
.question-card {
  width: 80%;
	border-radius: 15px;
}
.question {
  background-color: rgb(255, 189, 83);
  padding: 2rem 0 2rem 2rem;
	border-radius: 10px;
}
.object_answer {
  display: flex;
  /* justify-content: space-around; */
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
  padding: 4px;
}
</style>
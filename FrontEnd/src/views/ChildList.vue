<template>
  <div class="children">
    <div v-for="(child, i) in children" :key="i">
      <div class="child">
        <h4>
          <p>이름: {{ child[0] }}</p>
          <p>생년월일: {{ child[1] }}</p>
          <p v-if="child[2] === 'male'">성별: 남자</p>
          <p v-else>성별: 여자</p>
        </h4>
        <div>
          <router-link to="/question">
            <button class="childbutton">문진표 작성</button>
          </router-link>
          <router-link to="/counselorRecommend">
            <button class="childbutton">치료사 추천</button>
          </router-link>
          <router-link to="/question">
            <button class="childbutton">상담 내역</button>
          </router-link>
          <router-link to="/question">
            <button class="childbutton">놀이 기록</button>
          </router-link>
        </div>
      </div>
    </div>
    <div class="childcard" v-if="아동추가 == true">
      <h2>
        <p>이름: <input type="text" v-model="name"></p>
        <p for="cer_get">
          생년월일: <input type="date" id="cer_get" v-model="birth"/>
        </p>
        성별: 
        <select name="gender" id="gender" v-model="gender">
          <option value="" selected>선택</option>
          <option value="male">남</option>
          <option value="female">여</option>
        </select>
      </h2>
      <div style="display: flex; justify-content: flex-end;">
        <h2>
          <button class="addchildbutton2" @click="addChild">완료</button>
        </h2>
        <h2>
          <button class="addchildbutton2" @click="아동추가 = false">취소</button>
        </h2>
      </div>
    </div>
    <button class="addchildbutton" @click="아동추가 = true">+</button>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex'

export default {
  data() {
    return {
      아동추가: false,
      name: '',
      birth: '',
      gender: ''
    }
  },
  computed: {
    ...mapState({
      children: state => state.children.children
    }),
  },
  methods: {
    ...mapMutations(['ADD_CHILD']),
    addChild() {
      console.log('아동추가')
      if(this.name.length == 0 || this.birth.length == 0 || this.gender.length == 0){
        alert("Fill in the blank!")
        return
      }
      this.ADD_CHILD([this.name, this.birth, this.gender])
      
      // this.$axios.post(`${this.$store.state.host}/child`, {
      //   name: this.name,
      //   birth: this.birth,
      //   gender: this.gender
      // }).then(
      //   res => {
      //     console.log(res.data);
      //     this.$router.push("/login");
      // })
      this.name = ''
      this.birth = ''
      this.gender = ''
      this.아동추가 = false
    },
  }
}
</script>

<style>
.children {
  position: absolute;
  top: 0;
  width: 100%;
	margin-top: 10rem;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.child {
  display: flex;
  justify-content: space-around;
  align-items: center;
  border: solid 1px #ffffff;
  width: 80rem;
  height: 20rem;
  font-size: 25px;
  background-color: #feffa9;
  border-radius: 30px;
  margin: 0 0 15px 0;
}
.childbutton {
  background-color: rgb(255, 179, 93);
  border-radius: 10px;
  margin: 20px;
  padding: 5px;
}
.addchildbutton {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 80rem;
  height: 5rem;
  font-size: 50px;
  background-color: rgb(254, 255, 169);
  border-radius: 30px;
}
/* input {
  border: solid 2px;
} */
.childcard {
  border: solid 1px #ffffff;
  width: 80rem;
  height: 20rem;
  font-size: 25px;
  background-color: rgb(254, 255, 169);
  border-radius: 30px;
  padding: 30px;
  margin: 0 0 15px 0;
}
#gender {
  border: solid;
}
.addchildbutton2 {
  background-color: rgb(251, 190, 121);
  font-size: 25px;
  margin: 0 0 0 30px;
  padding: 1rem;
  border-radius: 10px;
}
</style>
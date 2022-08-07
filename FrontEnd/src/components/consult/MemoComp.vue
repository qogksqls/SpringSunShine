<template>
  <div class="memocard">
    <h1>메모장</h1>
    <hr style="margin: 10px">
    <p>
      <textarea id="memo" v-model="newMemo" placeholder="메모하세요."></textarea>
    </p>
    <button class="savebutton" @click="addMemo">저장</button>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'

export default {
  data() {
    return {
      newMemo: "",
    }
  },
  methods: {
    ...mapMutations(['ADD_MEMO']),
    addMemo(){
      if(this.newMemo.length == 0){
        alert("Fill in the blank!")
        return
      }
      const memo = this.newMemo.replace(/\n|\r\n/g,'<br>')
      // this.$store._modules.root.state.memos.list.push(memo)
      this.ADD_MEMO(memo)
      this.newMemo = ''
    },
  },
}
</script>

<style>
.memocard {
  padding: 20px;
}
#memo {
  border: solid 1px #ffffff;
  width: 100%;
  height: 20rem;
  font-size: 25px;
  background-color: rgb(254, 255, 169);
  border-radius: 30px;
  padding: 10px;
}
.savebutton {
  background-color: rgb(251, 190, 121);
  font-size: 25px;
  margin: 10px 0 0 90%;
  padding: 1rem;
  border-radius: 10px;
}
.memoList {
  border: solid;
  width: 100%;
  font-size: 25px;
  padding: 10px;
}
</style>
<template>
  <div class="memo">
    <memo-comp></memo-comp>
    <hr />
    <div class="memodata" v-for="(memo, i) in memolist" :key="i">
      <div style="display: flex; justify-content: space-between;">
        <h3>xx 아동의 o월0일 00시 진료 기록</h3>
        <button @click="deleteMemo(i)">[x]</button>
      </div>
      <hr style="margin: 10px" />
      <p v-html="getMemo(memo)"></p>
    </div>
  </div>
</template>

<script>
import memoComp from "@/components/consult/MemoComp.vue";
import { mapState, mapMutations } from "vuex";

export default {
  components: { memoComp },
  computed: {
    ...mapState({
      memolist: (state) => state.memos.list,
    }),
  },
  data() {
    return {
      result: this.memolist,
    };
  },
  methods: {
    ...mapMutations(["DELETE_MEMO"]),
    getMemo(result) {
      // return result.split('\n').join('<br>')
      return result;
    },
    deleteMemo(index) {
      this.DELETE_MEMO(index);
    },
  },
};
</script>

<style>
.memodata {
  margin: 10px 10px 0 10px;
  padding: 10px;
  border: solid #ffffff;
  border-radius: 15px;
  background-color: rgb(255, 210, 127);
  font-size: 2rem;
}
</style>

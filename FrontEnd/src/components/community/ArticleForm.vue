<template>
  <form @submit.prevent="onSubmit" class="form-box">
    <div>
      <label for="title"></label>
      <input v-model="newArticle.title" placeholder=" 글 제목" type="text" id="title" style="width: 100%; height: 50px; background-color: antiquewhite;" />
    </div>
    <hr>
    <div>
      <label for="content"></label>
      <textarea v-model="newArticle.content" type="text" id="content" placeholder=" 내용" style="width: 100%; height: 300px; background-color: antiquewhite;"></textarea>
    </div>
    <div class="buttons">
      <button class="btn btn-outline-primary">{{ action }}</button> |
      <router-link :to="{ name: 'articles' }">
        <button class="btn btn-secondary">Back</button>
      </router-link>
    </div>
  </form>
</template>

<script>
import { mapActions } from 'vuex'

  export default {
    name: 'ArticleForm',
    props: {
      article: Object,
      action: String,
    },
    data() {
      return {
        newArticle: {
          title: this.article.title,
          content: this.article.content,
        }
      }
    },

    methods: {
      ...mapActions(['createArticle', 'updateArticle']),
      onSubmit() {
        if (this.action === 'create') {
          this.createArticle(this.newArticle)
        } else if (this.action === 'update') {
          const payload = {
            pk: this.article.pk,
            ...this.newArticle,
          }
          this.updateArticle(payload)
        }
      },
    },
  }
</script>

<style scoped>
 *{ font-family: 'Noto Sans KR', sans-serif;}
 
.form-box {
  border: 1px solid rgb(245, 204, 204);
  margin-left: 32px;
  margin-right: 32px;
}
.buttons {
  float: right;
  /* display: flex; */
  justify-content: end;
}
</style>
<template>
  <div class="article-detail">
    <div style="border: solid; margin-top: 5px;">
      <span style="font-size: 25px">
        {{ article.user.username }} | 
        <span v-if="article.created_at === article.updated_at" style="font-size: 16px">{{ article.created_at.slice(0, 10) }}</span>
        <span v-if="article.created_at !== article.updated_at" style="font-size: 16px">{{ article.updated_at.slice(0, 10) }} (수정됨)</span>
      </span>
    </div>
    <div style="border: solid 1px; padding: 5px">
      <div class="title">
        <h2 style="font-weight: bold; margin-left: 5px;">{{ article.title }}</h2>
        <div v-if="isAuthor">
          <router-link :to="{ name: 'articleEdit', params: { articlePk } }">
            <button class="btn btn-outline-primary bt-sm">Edit</button>
          </router-link>
          |
          <button class="btn btn-outline-danger bt-sm" @click="deleteArticle(articlePk)">Delete</button>
        </div>
      </div>
      <p>
        {{ article.content }}
      </p>
      <div>
        좋아요! : 
        <button @click="likeArticle(articlePk)">
          <div v-if="likeCount">
          {{ likeCount }}</div>
          <div v-else>{{ likeCount }}</div>
        </button>
      </div>

      <hr>
      <comment-list :comments="article.comments"></comment-list>
    </div>
    <router-link :to="{ name: 'articles' }">
      <button class="btn btn-secondary" style="margin-top: 4px;">Back</button>
    </router-link>
  </div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex'
  import CommentList from '@/components/community/CommentList.vue'

  export default {
    name: 'ArticleDetail',
    components: { CommentList },
    data() {
      return {
        articlePk: this.$route.params.articlePk,
      }
    },
    computed: {
      ...mapGetters(['isAuthor', 'article']),
      likeCount() {
        return this.article.like_users?.length
      }
    },
    methods: {
      ...mapActions([
        'fetchArticle',
        'likeArticle',
        'deleteArticle',
      ])
    },
    created() {
      this.fetchArticle(this.articlePk)
    },
  }
</script>

<style scoped>
 *{ font-family: 'Noto Sans KR', sans-serif;}

.article-detail {
  margin-left: 32px;
  margin-right: 32px;
}
.title {
  display: flex;
  justify-content: space-between;
}
comment-list {
  width: 100%;
}
</style>
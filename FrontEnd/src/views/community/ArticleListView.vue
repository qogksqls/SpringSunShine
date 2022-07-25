<template>
  <div class="community-card" style="margin-top: 10rem; height: 30rem;">
    <h1 style="padding: 1rem;">커뮤니티</h1>

    <ul>
      <li v-for="article in articles" :key="article.pk" class="article" style="list-style-type: none;">
        <!-- 글 이동 링크 (제목) -->
        <router-link 
          :to="{ name: 'article', params: {articlePk: article.pk} }">
          <div class="title" style="margin-left: 5px">
            {{ article.title }}
          </div>
          <div class="content" style="margin-left: 5px">
            {{ article.content}}
          </div>
          <div class="remainder" style="margin-left: 5px;">
            <span v-if="article.created_at === article.updated_at">{{ article.created_at.slice(2, 10) + " " + article.created_at.slice(11, 19) }}</span>
            <span v-if="article.created_at !== article.updated_at">{{ article.updated_at.slice(2, 10) + " " + article.created_at.slice(11, 19) }} <span style="font-size: 2px;">(수정됨)</span> </span>
            
            <span>
              {{ article.user.username }}
              ------------------
              댓글 수: {{ article.comment_count }} | 좋아요: {{ article.like_count }}
            </span>
          </div>
        </router-link>
      </li>
    </ul>
    <div style="background-color: rgb(239, 232, 227);" class="create-article">
        <router-link :to="{ name: 'articleNew' }">
            <p style="margin-left: 5px;">글쓰기</p>
        </router-link>
    </div>
  </div>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'

  export default {
    name: 'ArticleList',
    computed: {
      ...mapGetters(['articles', 'isLoggedIn']),
    },
    methods: {
      ...mapActions(['fetchArticles']),
    },
    created() {
      this.fetchArticles()
    },
  }
</script>

<style scoped>
.community-card {
	margin: 3rem;
	background-color: antiquewhite;
	border-radius: 15px;
	position: relative;
}
h1 {
  color: rgb(5, 0, 2);
  margin-top: 10px;
}
.create-article {
	position: absolute;
	bottom: 0;
	right: 0;
	margin: 15px;
	display: flex;
	align-items: center;
	width: 4rem;
  border: 1px solid;
  cursor: pointer;
  height: 2rem;
	border-radius: 10px;
}
.article {
  border: 1px solid gray;
  cursor: pointer;
  margin-top: 5px;
  margin-right: 32px;
}
.article:hover {
  background-color: rgb(239, 232, 227);
}
a {
  text-decoration: none;
}
.title {
  color: black;
  font-size: large;
}
.content {
  color: gray;
  font-size: small;
}
.remainder {
  color: black;
  font-size: small;
}
</style>
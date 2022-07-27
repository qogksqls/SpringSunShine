<template>
  <div style="margin-top: 10rem">
    <!-- <h1 style="text-align: center;">New Article</h1> -->
    <article-form :article="article" action="create"></article-form>
  </div>
</template>

<script>
  import ArticleForm from '@/components/articlecomp/ArticleForm.vue'
  export default {
    name: 'AritcleNewView',
    components: { ArticleForm },
    data() {
      return {
        article: {
          pk: null,
          title: '',
          content: '',
        }
      }
    },
  }
</script>

<style scoped>
 *{ font-family: 'Luckiest Guy', cursive;}
 
 </style>
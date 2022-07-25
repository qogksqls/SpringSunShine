<template>
  <div v-if="login === true">
    <v-card
      color="grey lighten-4"
      flat
      height="50px"
      tile
    >
      <v-toolbar dense>
        <v-toolbar-title>
          <router-link to="/">LOGO</router-link>
        </v-toolbar-title>

        <v-spacer></v-spacer>
        
        <div class="nav">
          <li>
            <router-link to="/intro">소개</router-link>
          </li>
          <li>
            <a href="">상담하기</a>
          </li>
          <li>
            <a href="">혼자놀기</a>
          </li>
          <li>
            <a href="">게시판</a>
          </li>
          <li>
            <router-link to="/profile">마이페이지</router-link>
          </li>
          <li>
            <router-link to="/logout">로그아웃</router-link>
          </li>
        </div>
      </v-toolbar>
    </v-card>
  </div>
  <!-- <div v-else>
    <v-card
      color="grey lighten-4"
      flat
      height="50px"
      tile
    >
      <v-toolbar dense>
        <v-toolbar-title>
          <router-link to="/"><img src="@/assets/logo.png" style="margin: 19px 0 0 0;width: 50px; height: 50px;" alt=""></router-link>
        </v-toolbar-title>

        <v-spacer></v-spacer>
        
        <div class="nav">
          <li>
            <router-link to="/intro">소개</router-link>
          </li>
          <li>
            <router-link to="/articles">게시판</router-link>
          </li>
          <li>
            <router-link to="/login">로그인</router-link>
          </li>
          <li>
            <router-link to="/account">회원가입</router-link>
          </li>
        </div>

        <v-btn icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-heart</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
      </v-toolbar>
    </v-card>
  </div> -->
  <div v-else class="nav">
    <ul>
      <li>
        <router-link to="/intro">소개</router-link>
      </li>
      <li>
        <router-link to="/articles">게시판</router-link>
      </li>
    </ul>
    <router-link to="/"><img src="@/assets/logo.png" style="margin: 19px 0 0 0;width: 100px; height: 100px;" alt=""></router-link>
    <ul>
      <li>
        <router-link to="/login">로그인</router-link>
      </li>
      <li>
        <router-link to="/account">회원가입</router-link>
      </li>
    </ul>
  </div>


</template>

<script>
export default {
    // data: () => ({
    //   drawer: false,
    //   group: null,
    // }),
    computed: {
      check_login () {
        return this.$store.state.userStore.login
      }
    },
  watch: {
    check_login (val) {
      this.login = val
      },
      // group () {
      //   this.drawer = false
      // },
    },
}
</script>

<style scoped>
.nav {
  display: flex;
  justify-content: space-around;
  align-content: center;
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1;
  margin-top: 1rem;
  font-weight: bold;
}
.nav ul {
  display: flex;
  align-items: center;
}
.nav ul li a {
  text-decoration: none;
}

.nav ul li {
  margin: 20px;
  list-style: none;
  background-color: #fffdd6c2;
  font-size: 1.5rem;
  font-family: 'GangwonEdu_OTFBoldA';
}

.theme--light.v-toolbar.v-sheet {
    background-color: #fffdd6c2;
    font-size: 1.5rem;
    font-family: 'GangwonEdu_OTFBoldA';
}
.v-toolbar__title {
    font-size: 1.8rem;
}
</style>
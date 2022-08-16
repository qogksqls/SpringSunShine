<template>
  <header class="header-global">
    <base-nav class="navbar-main" transparent type="" effect="light" expand>
      <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
        <img src="/img/brand/blue.png" alt="logo" />

      </router-link>

      <div class="row" slot="content-header" slot-scope="{ closeMenu }">
        <div class="col-6 collapse-brand">
          <a
            href="https://demos.creative-tim.com/vue-argon-design-system/documentation/"
          >
            <img src="img/brand/blue.png" />
          </a>
        </div>
        <div class="col-6 collapse-close">
          <close-button @click="closeMenu"></close-button>
        </div>
      </div>

      <ul class="navbar-nav navbar-nav-hover align-items-lg-center ml-lg-auto">
        <base-dropdown tag="li" class="nav-item" v-if="isLoggedIn && this.$store.state.accounts.userid.slice(0, 1) === 'p'">
          <a
            slot="title"
            href="#"
            class="nav-link"
            data-toggle="dropdown"
            role="button"
          >
            <i class="ni ni-collection d-lg-none"></i>
            <span class="nav-link-inner--text"><b>치료교육</b></span>
          </a>
          <router-link to="/counselorRecommend" class="dropdown-item"
            >상담신청</router-link
          >
          <router-link to="/childReserveShow" class="dropdown-item"
            >상담일지(확인)</router-link
          >
          <router-link to="/playAlone" class="dropdown-item"
            >혼자놀기</router-link
          >
        </base-dropdown>
        <base-dropdown tag="li" class="nav-item" v-if="isLoggedIn">
          <a
            slot="title"
            href="#"
            class="nav-link"
            data-toggle="dropdown"
            role="button"
          >
            <i class="ni ni-collection d-lg-none"></i>
            <span class="nav-link-inner--text"><b>프로필보기</b></span>
          </a>
          <router-link to="/children" class="dropdown-item" v-if="this.$store.state.accounts.userid.slice(0, 1) === 'p'"
            >자녀관리</router-link
          >
          <router-link to="/profile" class="dropdown-item"
            >내프로필</router-link
          >
          <!-- <router-link to="/register" class="dropdown-item"
            >회원가입</router-link
          > -->
        </base-dropdown>

        <!--전문가는 해당 페이지만 보게함-->
        <li class="nav-link" v-if="isLoggedIn && this.$store.state.accounts.userid.slice(0, 1) === 't'">
          <router-link to="/counSchedule" class="nav-link-inner--text navbar p-0"
            ><b>예약관리</b></router-link
          >
        </li>

        <!-- 로그아웃 시 해당 메뉴만 보임-->
        <li class="nav-link">
          <router-link
            to="/counselorRecommend"
            class="nav-link-inner--text navbar p-0"
            ><b>상담사보기</b></router-link
          >
        </li>
        <li class="nav-link" v-if="!isLoggedIn">
          <router-link to="/login" class="nav-link-inner--text navbar p-0"
            ><b>로그인</b></router-link
          >
        </li>
        <li class="nav-link" v-else>
          <a class="nav-link-inner--text navbar p-0" style="cursor:pointer;" @click="logout()"
            ><b>로그아웃</b></a
          >
        </li>
      </ul>
    </base-nav>
  </header>
</template>
<script>
import BaseNav from "@/components/BaseNav";
import BaseDropdown from "@/components/BaseDropdown";
import CloseButton from "@/components/CloseButton";
import { mapGetters, mapActions } from "vuex"

export default {
  components: {
    BaseNav,
    CloseButton,
    BaseDropdown,
  },
  computed: {
    ...mapGetters(["isLoggedIn"])
  },
  methods: {
    ...mapActions(['logout'])
  },
};
</script>
<style>
.navbar {
  color: rgba(0, 0, 0, 0.5);
}
.navbar:hover {
  color: rgba(0, 0, 0, 0.7);
}
.navbar:focus {
  color: rgba(0, 0, 0, 0.7);
  text-shadow: 1px 1px 1px #fff;
}
</style>

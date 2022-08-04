<template>
  <header class="header-global">
    <base-nav class="navbar-main" transparent type="" effect="light" expand>
      <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
        <img src="img/brand/white.png" alt="logo" />
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
        <base-dropdown tag="li" class="nav-item">
          <a
            slot="title"
            href="#"
            class="nav-link"
            data-toggle="dropdown"
            role="button"
          >
            <i class="ni ni-collection d-lg-none"></i>
            <span class="nav-link-inner--text">치료교육</span>
          </a>
          <router-link to="/counselorRecommend" class="dropdown-item"
            >상담신청</router-link
          >
          <router-link to="/profile" class="dropdown-item"
            >상담일지(확인)</router-link
          >
          <router-link to="/login" class="dropdown-item">혼자놀기</router-link>
        </base-dropdown>
        <base-dropdown tag="li" class="nav-item">
          <a
            slot="title"
            href="#"
            class="nav-link"
            data-toggle="dropdown"
            role="button"
          >
            <i class="ni ni-collection d-lg-none"></i>
            <span class="nav-link-inner--text">프로필보기</span>
          </a>
          <router-link to="/landing" class="dropdown-item"
            >자녀관리</router-link
          >
          <router-link to="/profile" class="dropdown-item"
            >내프로필</router-link
          >
          <router-link to="/" class="dropdown-item">로그아웃</router-link>
          <router-link to="/register" class="dropdown-item"
            >회원가입</router-link
          >
        </base-dropdown>

        <!--전문가는 해당 페이지만 보게함-->
        <li class="nav-link">
          <router-link to="/" class="nav-link-inner--text navbar p-0"
            >예약관리</router-link
          >
        </li>

        <!-- 로그아웃 시 해당 메뉴만 보임-->
        <li class="nav-link">
          <router-link
            to="/counselorRecommend"
            class="nav-link-inner--text navbar p-0"
            >상담사보기</router-link
          >
        </li>
        <li class="nav-link">
          <router-link to="/login" class="nav-link-inner--text navbar p-0"
            >로그인</router-link
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

export default {
  components: {
    BaseNav,
    CloseButton,
    BaseDropdown,
  },
};
</script>
<style>
.navbar {
  color: #fff;
}
.navbar:hover {
  color: #acacac;
}
</style>

<template>
    <div class="profile-page">
        <section class="section-profile-cover section-shaped my-0">
            <div class="shape shape-style-1 shape-primary shape-skew alpha-4">
            </div>
        </section>
        <section class="section section-skew">
            <div class="container">
                <card shadow class="card-profile mt--300" no-body>
                    <div class="px-4">
                        <div class="row justify-content-center">
                            <div class="col-lg-3 order-lg-2">
                                <div class="card-profile-image">
                                    <a href="#">
                                        <img v-lazy="'img/theme/team-4-800x800.jpg'" class="rounded-circle">
                                    </a>
                                </div>
                            </div>
                            <div class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center">
                                <div class="card-profile-actions py-4 mt-lg-0">
                                  <router-link to="/update">
                                    <base-button type="info" size="sm" class="mr-4">회원정보 수정</base-button>
                                  </router-link>
                                    <base-button type="default" size="sm" class="float-right">회원 탈퇴</base-button>
                                </div>
                            </div>
                            <!-- <div class="col-lg-4 order-lg-1">
                                <div class="card-profile-stats d-flex justify-content-center">
                                    <div>
                                        <span class="heading">22</span>
                                        <span class="description">Friends</span>
                                    </div>
                                    <div>
                                        <span class="heading">10</span>
                                        <span class="description">Photos</span>
                                    </div>
                                    <div>
                                        <span class="heading">89</span>
                                        <span class="description">Comments</span>
                                    </div>
                                </div>
                            </div> -->
                        </div>
                        <div class="text-center mt-5">
                            <h3>
                              {{ userInfo["parentInfo"]["name"] }}
                                <span class="font-weight-light"></span>
                            </h3>
                            <div class="h6 font-weight-300"><i class="ni location_pin mr-2"></i>
                              {{ userInfo["parentInfo"]["email"] }}</div>
                            <div class="h6 mt-4"><i class="ni business_briefcase-24 mr-2"></i>
                              {{ userInfo["parentInfo"]["phone"] }}</div>
                            <div><i class="ni education_hat mr-2"></i>
                              {{ userInfo["parentInfo"]["address"] }}</div>
                        </div>
                        <div class="mt-5 py-5 border-top text-center">
                            <div class="row justify-content-center">
                                <div class="col-lg-9">
                                    <p>자기소개</p>
                                    <a href="#">Show more</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </card>
            </div>
        </section>
    </div>
</template>
<script>
import axios from 'axios'

export default {
  name: 'profile',
  data() {
    return {
      userInfo: {}
    }
  },
  created() {
    console.log("프로필 페이지")
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/user/${this.$store.state.accounts.userid}`,
      method: 'get',
      headers: { Authorization: `Bearer ${this.$store.state.accounts.accessToken}`}
    })
      .then((res) => {
        if (this.$store.state.accounts.userid.slice(0, 1) === 't') {
          console.log(res.data.theraInfo)
          this.userInfo = res.data.theraInfo
        } else {
          console.log(res.data)
          this.userInfo = res.data
        }
      })
      .catch(err => {
        console.log(err.response)
      })
    // axios({  // accessToken 재발급
    //   url: `https://i7a606.q.ssafy.io/service-api/auth/refresh/${this.$store.state.accounts.userid}`,
    //   method: 'get',
    //   headers: { Authorization: `Bearer ${this.$store.state.accounts.refreshToken}`}
    // })
    //   .then(res => {
    //     console.log(res.data)
    //     this.$store.state.accounts.accessToken = res.data.accessToken
    //   })
    //   .catch(err => {
    //     console.log(err.response)
    //   })
  }
};
</script>
<style>
</style>

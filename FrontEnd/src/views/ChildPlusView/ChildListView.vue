<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class="px-lg-5 card-profile mt--300" no-body>
          <!--상담일지목록 시작-->
          <div class="wrap_content col col-lg-12 p-md-5">
            <!--위에 타이틀 부분-->
            <div class="col-md-12 row m-2">
              <h3>
                <div class="text-muted text-left my-3">
                  <b style="color:#8898aa;">자녀관리</b>
                </div>
              </h3>

              <!--추가버튼-->
              <div class="chogabtn row col-md-2 col justify-content-center">
                <button class="childbtn" @click="아동추가 = true">
                  <i class="fa fa-user-plus fa-3x" aria-hidden="true"></i>

                  <div class="row justify-content-center text-center">
                    <b>아동추가</b>
                  </div>
                </button>
              </div>
              <!--추가버튼-->
            </div>
            <!--위 타이틀 완료-->

            <div class="children col">
              <div
                v-for="(child, i) in children"
                :key="i"
                class="col-sm-4 mb-5"
              >
                <div class="child py-3">
                  <h4 class="col col-sm-12">
                    <img :src="`data:image/png;base64,${child['profileUrl']}`" style="width: 150px; height: 150px">
                    <p>이름: <nbsp></nbsp>{{ child["name"] }}</p>
                    <p>생년월일: {{ child["birth"].slice(0, 10) }}</p>
                    <p>성별: {{ child["gender"] }}</p>
                  </h4>
                  <div
                    class="row text-center justify-content-center col-sm-12 mx-0"
                  >
                    <div class="col-sm-6 px-0" v-if="child['surveyFlag']">
                      <base-button
                        type="default"
                        class="childbutton"
                        @click="moveSurveyResult(i)"
                      >
                        문진표보기
                      </base-button>
                    </div>
                    <div class="col-sm-6 px-0" v-else>
                      <base-button
                        type="default"
                        class="childbutton"
                        @click="moveSurvey(i)"
                      >
                        문진표작성
                      </base-button>
                    </div>
                    <div class="col-sm-6 px-0" v-if="child['surveyFlag']">
                      <base-button
                        type="default"
                        class="childbutton"
                        @click="moveCounselorRecom(i)"
                      >
                        상담사추천
                      </base-button>
                    </div>
                    <div class="col-sm-6 px-0" v-else>
                      <go-to-survey></go-to-survey>
                    </div>

                    <!-- <router-link to="/childReserveShow" class="col-lg-6 px-0"> -->
                    <base-button type="default" class="childbutton" @click="moveHistory(i)">
                      상담일정
                    </base-button>
                    <!-- </router-link> -->

                    <router-link to="/survey" class="col-sm-6 px-0">
                      <base-button type="default" class="childbutton">
                        놀이 기록
                      </base-button>
                    </router-link>
                  </div>
                </div>
              </div>
              <div class="childcard col-lg-4 mb-5 py-3" v-if="아동추가 == true">
                <div class="col-sm-12">
                  <div class="row mb-4">
                    <p class="col-sm-5 m-0">프로필사진</p>
                    <form>
                      <input
                        type="file"
                        id="photo"
                        name="photo"
                        class="col-sm-7 text-right align-self-end"
                      />
                    </form>
                    <div class="col-sm-5"></div>
                    <img v-if="image" :src="image" width="100" height="100" />
                  </div>
                  <div class="row mb-2">
                    <p class="col-sm-5">이름</p>
                    <base-input
                      placeholder="이름을 적어주세요"
                      type="text"
                      v-model="name"
                      class="col-sm-7"
                    />
                  </div>

                  <div for="cer_get" class="row mb-2">
                    <p class="col-sm-5">생년월일</p>
                    <base-input
                      type="date"
                      id="cer_get"
                      v-model="birth"
                      class="col-sm-7"
                    />
                  </div>
                  <div class="row mb-2">
                    <p class="col-sm-5">
                      성별
                    </p>
                    <select
                      name="gender"
                      id="gender"
                      v-model="gender"
                      class="ml-3 col-sm-6 form-control"
                    >
                      <option value="" selected>선택</option>
                      <option value="남자">남</option>
                      <option value="여자">여</option>
                    </select>
                  </div>
                </div>
                <div class="text-center justify-content-center my-3">
                  <base-button type="primary" @click="addChild">
                    <b>완료</b>
                  </base-button>
                  <base-button type="primary" @click="아동추가 = false">
                    <b>취소</b>
                  </base-button>
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
import axios from "axios";
import GoToSurvey from "../../components/Reserve/GoToSurvey.vue";

export default {
  name: "ChildListView",
  components: { GoToSurvey },
  data() {
    return {
      아동추가: false,
      name: "",
      birth: "",
      gender: "",
      image: "",
      children: [],
    };
  },
  methods: {
    addChild() {
      console.log("아동추가");
      var frm = new FormData()
      var photoFile = document.getElementById("photo")
      console.log(photoFile)
      const childInfo = JSON.stringify({
        parent_id: this.$store.state.accounts.userid,
        name: this.name,
        birth: this.birth,
        gender: this.gender,
        profile_url: '',
        survey_flag: 0,
      })
      // console.log(childInfo)
      // console.log(photoFile.files[0])
      frm.append('childInfo', new Blob([childInfo], { type: "application/json" }));
      frm.append('profile', photoFile.files[0]);
      if (
        this.name.length == 0 ||
        this.birth.length == 0 ||
        this.gender.length == 0
      ) {
        alert("아동정보를 모두 채워주세요");
        return;
      } else {
        axios({
          url: "https://i7a606.q.ssafy.io/service-api/child/register",
          method: "post",
          data: frm,
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
          .then((res) => {
            console.log(res.data);
            router.push({ name: "children" });
          })
          .catch((err) => {
            console.log(err.response);
          });
        this.아동추가 = false;
        (this.name = ""),
          (this.birth = ""),
          (this.gender = ""),
          (this.image = "");
      }
    },
    moveSurvey(index) {
      this.$router.push({ name: "survey", params: this.children[index] });
    },
    moveCounselorRecom(index) {
      this.$router.push({
        name: "counselorRecommend",
        params: this.children[index],
      });
    },
    moveSurveyResult(index) {
      this.$router.push({ name: "surveyresult", params: this.children[index] });
    },
    moveHistory(index) {
      this.$router.push({ name: "childReserveShow", params: this.children[index] });
    }
  },
  created() {
    console.log(`userid: ${this.$store.state.accounts.userid}`);
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/child/${
        this.$store.state.accounts.userid
      }`,
      method: "get",
    })
      .then((res) => {
        console.log(res.data);
        this.children = res.data;
      })
      .catch((err) => {
        console.log(err.response);
      });

    axios({  // accessToken 재발급
      url: `https://i7a606.q.ssafy.io/service-api/auth/refresh/${this.$store.state.accounts.userid}`,
      method: 'get',
      headers: { Authorization: `Bearer ${this.$store.state.accounts.refreshToken}`}
    })
      .then(res => {
        console.log(res.data)
        this.$store.state.accounts.accessToken = res.data.accessToken
      })
      .catch(err => {
        console.log(err.response)
      })
  },
};
</script>

<style scoped>
@media (min-width: 768px) and (max-width: 991px) {
  .card {
    height: 100vh;
  }
}
.card {
  position: relative;
}
.child {
  border: 1px solid #dcdcdc;
  border-radius: 15px;
}
.childbutton {
  margin: 20px 10px 20px 10px;
}

.childcard {
  width: 25;
  border-radius: 15px;
  border: 1px solid #dcdcdc;
}
.childbtn {
  background-color: rgba(255, 255, 255, 0);
  border: none;
  cursor: pointer;
}
button:focus,
button:active {
  outline: none;
  box-shadow: none;
}
.fa-user-plus {
  color: #8898aa;
}
.fa-user-plus:hover {
  color: #ffdcb8;
}
</style>

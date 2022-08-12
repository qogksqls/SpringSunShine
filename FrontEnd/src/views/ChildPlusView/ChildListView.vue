<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4"></div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class="px-5 px-lg-5 card-profile mt--300" no-body>
          <!--상담일지목록 시작-->
          <div class="wrap_content col col-lg-12 p-5">
            <!--위에 타이틀 부분-->
            <div class="col-lg-12 row mb-2">
              <h3 class="col-lg-10">
                <div class="text-muted text-left mb-3">
                  <b style="color:#8898aa;">상담받을 아이를 선택하세요</b>
                </div>
              </h3>

              <!--추가버튼-->
              <div class="chogabtn row col-lg-2 justify-content-center">
                <button class="childbtn" @click="아동추가 = true">
                  <i class="fa fa-user-plus fa-3x" aria-hidden="true"></i>

                  <div class="col-sm-12 justify-content-center text-center">
                    <b>아동추가</b>
                  </div>
                </button>
              </div>
              <!--추가버튼-->
            </div>
            <!--위 타이틀 완료-->

            <div class="children row col-lg-12">
              <div
                v-for="(child, i) in children"
                :key="i"
                class="col-lg-4 mb-5"
              >
                <div class="child py-3">
                  <h4 class="col col-md-12">
                    <p>이름: <nbsp></nbsp>{{ child[0] }}</p>
                    <p>생년월일: {{ child[1] }}</p>
                    <p v-if="child[2] === 'male'">
                      성별: 남자
                    </p>
                    <p v-else>성별: 여자</p>
                    <input type="file" />
                  </h4>
                  <div
                    class="row text-center justify-content-center col-lg-12 mx-0"
                  >
                    <router-link to="/survey" class="col-lg-6 px-0">
                      <base-button type="default" class="childbutton"
                        >문진표작성</base-button
                      >
                    </router-link>
                    <router-link to="/counselorRecommend" class="col-lg-6 px-0">
                      <base-button type="default" class="childbutton"
                        >상담사추천</base-button
                      >
                    </router-link>
                    <router-link to="/childReserveShow" class="col-lg-6 px-0">
                      <base-button type="default" class="childbutton">
                        상담 내역
                      </base-button>
                    </router-link>
                    <router-link to="/survey" class="col-lg-6 px-0">
                      <base-button type="default" class="childbutton">
                        놀이 기록
                      </base-button>
                    </router-link>
                  </div>
                </div>
              </div>
              <!-- <child-comp></child-comp> -->
              <div class="childcard col-lg-4 mb-5 py-3" v-if="아동추가 == true">
                <div class="col-md-12">
                  <div class="row">
                    <p class="col-md-5">이름</p>
                    <base-input
                      placeholder="이름을 적어주세요"
                      type="text"
                      v-model="name"
                      class="col-md-7"
                    />
                  </div>

                  <div for="cer_get" class="row">
                    <p class="col-md-5">생년월일</p>
                    <base-input
                      type="date"
                      id="cer_get"
                      v-model="birth"
                      class="col-md-7"
                    />
                  </div>
                  <div class="row">
                    <p class="col-md-5">
                      성별
                    </p>
                    <select
                      name="gender"
                      id="gender"
                      v-model="gender"
                      class="ml-3 col-md-6 form-control"
                    >
                      <option value="" selected>선택</option>
                      <option value="male">남</option>
                      <option value="female">여</option>
                    </select>
                  </div>
                </div>
                <div class="text-center justify-content-center my-3">
                  <base-button type="primary" @click="addChild">
                    <b>
                      완료
                    </b>
                  </base-button>
                  <base-button type="primary" @click="아동추가 = false">
                    <b>
                      취소
                    </b>
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
// import childComp from '@/components/childComp.vue'
import { mapState, mapMutations } from "vuex";

export default {
  // components: { childComp },
  data() {
    return {
      아동추가: false,
      name: "",
      birth: "",
      gender: "",
    };
  },
  computed: {
    ...mapState({
      children: (state) => state.children.children,
    }),
  },
  methods: {
    ...mapMutations(["ADD_CHILD"]),
    addChild() {
      if (
        this.name.length == 0 ||
        this.birth.length == 0 ||
        this.gender.length == 0
      ) {
        alert("빈칸을 채워주세요");
        return;
      }
      this.ADD_CHILD([this.name, this.birth, this.gender]);
      this.아동추가 = false;
    },
  },
};
</script>

<style scoped>
.child {
  border: 1px solid #dcdcdc;
  border-radius: 15px;
}
.children {
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

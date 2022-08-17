<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default"></div>
    <div class="container pt-lg-md py-lg-5">
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="p-lg-5"
            class="border-0"
          >
            <template>
              <h3 v-if="$route.params.isTherapist">
                <div class="text-muted text-left mb-3">
                  <b>상담사 회원가입</b>
                </div>
              </h3>
              <h3 v-else>
                <div class="text-muted text-left my-lg-4">
                  <b>보호자 회원가입</b>
                </div>
              </h3>
              <br /><br />
              <div
                class="wrap_content row justify-content-center align-self-center"
              >
                <!--회원가입 폼 이름 작성 start-->

                <div class="row col-lg-12 mb-2">
                  <label for="name" class="col col-lg-4 mt-2">이름</label>

                  <input
                    type="text"
                    placeholder="이름을 적어주세요"
                    class="col-lg-8  form-control"
                    id="name"
                    v-model="name"
                  />
                </div>
                <!--회원가입 폼 이름 작성 end-->

                <!--회원가입 폼 아이디 작성 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="id" class="col col-lg-4 mt-2">아이디</label>
                  <input
                    type="text"
                    class="col-lg-6  form-control"
                    id="id"
                    placeholder="아이디를 적어주세요"
                    v-model="id"
                  />
                  <div class="col-lg-1"></div>
                  <base-button
                    outline
                    type="primary"
                    size="sm"
                    class="my-1 col col-lg-1 p-1"
                    @click="checkId"
                    >중복검사</base-button
                  >
                </div>
                <!--회원가입 폼 아이디 작성 end-->

                <!--회원가입 폼 비밀번호 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="pw" class="col col-lg-4 mt-2">비밀번호</label>
                  <input
                    type="password"
                    class="col-lg-8  form-control"
                    id="pw"
                    placeholder="비밀번호를 적어주세요"
                    v-model="password1"
                  />
                </div>
                <!--회원가입 폼 비밀번호 end-->

                <!--회원가입 폼 비밀번호확인 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="pw2" class="col col-lg-4 mt-2"
                    >비밀번호확인</label
                  >
                  <input
                    type="password"
                    class="col-lg-8  form-control"
                    id="pw2"
                    placeholder="비밀번호를 확인해 주세요"
                    v-model="password2"
                  />
                </div>
                <div v-if="password1 !== '' || password2 !== ''">
                  <div v-if="password2 !== password1">
                    <h6>비밀번호 값이 다릅니다.</h6>
                  </div>
                  <div v-else>
                    확인되었습니다.
                  </div>
                </div>
                <!--회원가입 폼 비밀번호확인 end-->

                <!--회원가입 폼 이메일 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="email" class="col col-lg-4 mt-2">이메일</label>
                  <input
                    type="email"
                    class="col-lg-7 form-control"
                    id="email"
                    placeholder="이메일을 입력해 주세요"
                    v-model="email"
                  />
                  <base-button
                    outline
                    type="primary"
                    size="sm"
                    class="mt-2 mr-0 col col-lg-1 p-1"
                    @click="getEmailCode"
                    >인증받기</base-button
                  >
                </div>
                <div class="col-lg-12 row mb-2">
                  <label for="email" class="col col-lg-4 mt-2"></label>
                  <div class="col-lg-1"></div>
                  <input
                    type="text"
                    class="col-lg-6 form-control"
                    id="email"
                    placeholder="인증번호 입력"
                    v-model="emailCode1"
                  />
                  <div v-if="emailCode1 !== ''">
                    <base-button
                      outline
                      type="primary"
                      size="sm"
                      @click="checkEamilCode"
                    >
                      확인
                    </base-button>
                  </div>
                </div>
                <!--회원가입 폼 이메일 end-->

                <!--회원가입 폼 연락처 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="tel" class="col col-lg-4 mt-2">연락처</label>
                  <input
                    type="tel"
                    class="col-lg-8 form-control"
                    id="tel"
                    placeholder="연락처를 입력해 주세요"
                    v-model="phone"
                  />
                </div>
                <!--회원가입 폼 연락처 end-->

                <!--회원가입 폼 주소 start-->
                <div class="col-lg-12 row  mb-2">
                  <label for="address" class="col col-lg-4 mt-2">주소</label>
                  <input
                    type="text"
                    class="col-lg-8 form-control"
                    id="address"
                    placeholder="주소를 입력해 주세요"
                    v-model="address"
                  />
                </div>
                <!--회원가입 폼 연락처 end-->
                <div v-if="$route.params.isTherapist">
                  <counselor @counselor="counselor_data" />
                </div>

                <!--회원가입 폼 학력 end-->
              </div>
              <div v-if="$route.params.isTherapist" class="mt-2 text-center">
                <base-button type="primary" class="py-1" @click="signinTeacher"
                  >상담사 회원가입</base-button
                >
              </div>
              <div v-else class="mt-2 text-center">
                <base-button type="primary" class="py-1" @click="signinParent"
                  >보호자 회원가입</base-button
                >
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import Counselor from "./components/Signin/Counselor.vue";

export default {
  components: { Counselor },
  data() {
    return {
      name: "",
      id: "",
      password1: "",
      password2: "",
      email: "",
      phone: "",
      address: "",
      thera_intro: "",
      profile_url: "",
      expertises: [],
      academicCareers: [],
      careers: [],
      licences: [],

      checkid: false,

      checkEmail: true,
      emailCode1: '',
      emailCode2: ''
    };
  },
  methods: {
    checkId() {
      this.$axios
        .get(`${this.$store.state.host}/service-api/user/checkid/${this.id}`)
        .then((res) => {
          if (res.data === "success") {
            alert("사용 가능한 아이디입니다.");
            this.checkid = true;
          } else {
            alert("이미 사용된 아이디입니다.");
            this.id = "";
            this.checkid = false;
          }
        });
    },
    getEmailCode() {
      console.log("이메일 인증");
      this.$axios
        .post(`${this.$store.state.host}/service-api/mail`, {
          email: this.email,
        })
        .then((res) => {
          this.emailCode2 = res.data;
        });
    },
    checkEamilCode() {
      if (this.emailCode1 === this.emailCode2) {
        this.checkEmail = true;
        alert("이메일 인증이 완료되었습니다.");
      } else {
        this.emailCode1 = "";
        alert("인증번호가 불일치합니다. 다시 입력해주세요.");
      }
    },
    counselor_data(inputDatas) {
      // console.log(inputDatas)
      this.profile_url = inputDatas.profile_url;
      this.expertises = inputDatas.expertise_no;
      this.academicCareers = inputDatas.academicCareers;
      this.careers = inputDatas.careers;
      this.licences = inputDatas.licences;
      this.thera_intro = inputDatas.thera_intro;
      // console.log(this.profile_url.files[0])
      // console.log(this.expertise)
      // console.log(this.academicCareers)
      // console.log(this.careers)
      // console.log(this.licences)
      // console.log(this.thera_intro)
    },
    signinTeacher() {
      console.log("상담사 회원가입");
      console.log(`${this.$store.state.host}/service-api/user/therapist`);
      var frm = new FormData();
      const theraInfo = JSON.stringify({
        id: this.id,
        password: this.password1,
        name: this.name,
        email: this.email,
        phone: this.phone,
        address: this.address,
        profile_url: 'null',
        expertise: this.expertises,
        academicCareers: this.academicCareers,
        careers: this.careers,
        licences: this.licences,
        thera_intro: this.thera_intro,
      })
      frm.append("theraInfo", new Blob([theraInfo], { type: "application/json" }));
      frm.append("profile", this.profile_url.files[0])
      if (
        this.id &&
        this.checkid === true &&
        this.password1 &&
        this.password2 &&
        this.name &&
        this.phone &&
        this.address &&
        this.email &&
        this.checkEmail === true
      ) {
        this.$axios({
          url: 'https://i7a606.q.ssafy.io/service-api/user/therapist',
          method: 'post',
          data: frm,
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
          .then((res) => {
            console.log(res.data)
            this.$router.push("/login");
          })
          .catch((err) => {
            console.log(err.response)
          })
      } else {
        if (!this.name) {
          alert("성함을 입력해주세요.");
        } else if (!this.id) {
          alert("아이디를 입력해주세요.");
        } else if (this.checkid === false) {
          alert("아이디 중복체크를 진행해주세요")
        } else if (!this.password1) {
          alert("비밀번호를 입력해주세요.");
        } else if (!this.password2) {
          alert("비밀번호 확인을 진행해주세요.");
        } else if (!this.email) {
          alert("이메일을 입력해주세요.");
        } else if (this.checkEmail === false) {
          alert("이메일 인증번호 확인을 진행해주세요.")
        } else if (!this.phone) {
          alert("연락처를 입력해주세요.");
        } else if (!this.address) {
          alert("주소를 입력해주세요.");
        }
      }
    },
    signinParent() {
      console.log("보호자 회원가입");
      if (
        this.id &&
        this.checkid === true &&
        this.password1 &&
        this.password2 &&
        this.name &&
        this.phone &&
        this.address &&
        this.email &&
        this.checkEmail === true
      ) {
        this.$axios
          .post(`${this.$store.state.host}/service-api/user/parent`, {
            id: this.id,
            password: this.password1,
            name: this.name,
            email: this.email,
            phone: this.phone,
            address: this.address,
          })
          .then((res) => {
            console.log(res.data);
            this.$router.push("/login");
          });
      } else {
        if (!this.name) {
          alert("보호자님의 성함을 입력해주세요.");
        } else if (!this.id) {
          alert("아이디를 입력해주세요.");
        } else if (this.checkid === false) {
          alert("아이디 중복체크를 진행해주세요")
        } else if (!this.password1) {
          alert("비밀번호를 입력해주세요.");
        } else if (!this.password2) {
          alert("비밀번호 확인을 진행해주세요.");
        } else if (!this.email) {
          alert("이메일을 입력해주세요.");
        } else if (this.checkEmail === false) {
          alert("이메일 인증번호 확인을 진행해주세요.")
        } else if (!this.phone) {
          alert("연락처를 입력해주세요.");
        } else if (!this.address) {
          alert("주소를 입력해주세요.");
        }
      }
    },
  },
};
</script>

<style scoped>
body {
  background: linear-gradient(87deg, #ffdcb8 0, #fffab8 100%) !important;
}
input {
  height: 40px;
  border: 1px solid #bebebe;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: #fff;
  color: #999;
  cursor: text;
}
button {
  height: 30px;
  border-radius: 10px;
}
</style>

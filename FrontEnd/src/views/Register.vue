<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default"></div>
    <div class="container pt-lg-md">
      <div class="row justify-content-center">
        <div class="col-lg-10">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <h3>
                <div class="text-muted text-left mb-3">
                  <b>회원가입</b>
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
                  />
                  <div class="col-lg-1"></div>
                  <base-button
                    outline
                    type="primary"
                    size="sm"
                    class="my-1 col col-lg-1 p-1"
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
                  />
                </div>
                <!--회원가입 폼 비밀번호확인 end-->

                <!--회원가입 폼 이메일 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="email" class="col col-lg-4 mt-2">이메일</label>
                  <input
                    type="email"
                    class="col-lg-8 form-control"
                    id="email"
                    placeholder="이메일을 입력해 주세요"
                  />
                </div>
                <div class="col-lg-12 row mb-2">
                  <label for="email" class="col col-lg-4 mt-2"></label>
                  <base-button
                    outline
                    type="primary"
                    size="sm"
                    class="mt-2 mr-0 col col-lg-1 p-1"
                    >인증받기</base-button
                  >
                  <div class="col-lg-1"></div>
                  <input
                    type="text"
                    class="col-lg-6 form-control"
                    id="email"
                    placeholder="인증번호 입력"
                  />
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
                  />
                </div>
                <!--회원가입 폼 연락처 end-->

                <counselor />

                <!--회원가입 폼 학력 end-->
              </div>
              <div class="mt-2 text-center">
                <base-button type="primary" class="py-1">회원가입</base-button>
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
};
</script>
<style scoped>
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

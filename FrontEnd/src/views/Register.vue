<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
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
                  <label for="name" class="col col-lg-2 mt-2">이름</label>

                  <input
                    type="text"
                    placeholder="이름을 적어주세요"
                    class="col-lg-5  form-control"
                    id="name"
                  />
                </div>
                <!--회원가입 폼 이름 작성 end-->

                <!--회원가입 폼 아이디 작성 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="id" class="col col-lg-2 mt-2">아이디</label>
                  <input
                    type="text"
                    class="col-lg-4  form-control"
                    id="id"
                    placeholder="아이디를 적어주세요"
                  />

                  <base-button outline type="danger" size="sm" class=" m-2"
                    >중복검사</base-button
                  >
                </div>
                <!--회원가입 폼 아이디 작성 end-->

                <!--회원가입 폼 비밀번호 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="pw" class="col col-lg-2 mt-2">비밀번호</label>
                  <input
                    type="password"
                    class="col-lg-5  form-control"
                    id="pw"
                    placeholder="비밀번호를 적어주세요"
                  />
                </div>
                <!--회원가입 폼 비밀번호 end-->

                <!--회원가입 폼 비밀번호확인 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="pw2" class="col col-lg-2 mt-2"
                    >비밀번호확인</label
                  >
                  <input
                    type="password"
                    class="col-lg-5  form-control"
                    id="pw2"
                    placeholder="비밀번호를 확인해 주세요"
                  />
                </div>
                <!--회원가입 폼 비밀번호확인 end-->

                <!--회원가입 폼 이메일 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="email" class="col col-lg-2 mt-2">이메일</label>
                  <input
                    type="email"
                    class="col-lg-5  form-control"
                    id="email"
                    placeholder="이메일을 입력해 주세요"
                  />
                </div>
                <div class="col-lg-12 row mb-2">
                  <label for="email" class="col col-lg-2 mt-2"></label>
                  <base-button
                    outline
                    type="danger"
                    size="sm"
                    class="mt-2 mr-0 col col-lg-1 p-1"
                    >인증받기</base-button
                  >
                  <div class="col-lg-1"></div>
                  <input
                    type="text"
                    class="col-lg-3 form-control"
                    id="email"
                    placeholder="인증번호 입력"
                  />
                </div>
                <!--회원가입 폼 이메일 end-->

                <!--회원가입 폼 연락처 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="tel" class="col col-lg-2 mt-2">연락처</label>
                  <input
                    type="tel"
                    class="col-lg-5 form-control"
                    id="tel"
                    placeholder="연락처를 입력해 주세요"
                  />
                </div>
                <!--회원가입 폼 연락처 end-->

                <!--회원가입 폼 주소 start-->
                <div class="col-lg-12 row  mb-2">
                  <label for="address" class="col col-lg-2 mt-2">주소</label>
                  <input
                    type="text"
                    class="col-lg-5 form-control"
                    id="address"
                    placeholder="주소를 입력해 주세요"
                  />
                </div>
                <!--회원가입 폼 연락처 end-->

                <!--상담사 회원가입 폼 시작-->

                <!--상담사 폼 학력 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="grade" class="col col-lg-1 mt-2">학력</label>

                  <base-button
                    type="secondary"
                    class="ni ni-fat-add p-1"
                  ></base-button>
                  <div class="col-lg-12 row teach py-3">
                    <label for="school" class="col col-lg-2 mt-2">학교명</label>
                    <input
                      type="text"
                      class="col-lg-3 form-control"
                      id="school"
                      placeholder="학교명"
                    />
                    <div class="col-lg-1"></div>
                    <label for="major" class="col col-lg-2 mt-2">전공</label>
                    <input
                      type="text"
                      class="col-lg-3 form-control"
                      id="major"
                      placeholder="전공"
                    />
                    <label for="inyear" class="col col-lg-2 mt-3 ">입학</label>
                    <input
                      type="date"
                      class="col-lg-3 form-control mt-2"
                      id="year"
                    />
                    <div class="col-lg-1"></div>
                    <label for="year" class="col col-lg-2 mt-3 ">졸업</label>
                    <input
                      type="date"
                      class="col-lg-3 form-control mt-2"
                      id="year"
                    />
                  </div>
                </div>

                <!--상담사 폼 학력 end-->

                <!--상담사 폼 경력 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="grade" class="col col-lg-1 mt-2">경력</label>

                  <base-button
                    type="secondary"
                    class="ni ni-fat-add p-1"
                  ></base-button>
                  <div class="col-lg-12 row teach py-3">
                    <label for="job" class="col col-lg-2 mt-2">회사명</label>
                    <input
                      type="text"
                      class="col-lg-3 form-control"
                      id="job"
                      placeholder="회사명"
                    />
                    <div class="col-lg-1"></div>
                    <label for="rank" class="col col-lg-2 mt-2">직급</label>
                    <input
                      type="text"
                      class="col-lg-3 form-control"
                      id="rank"
                      placeholder="직급"
                    />
                    <label for="inyear" class="col col-lg-2 mt-3 "
                      >재직기간</label
                    >
                    <input
                      type="date"
                      class="col-lg-3 form-control mt-2"
                      id="year"
                    />
                    <div class="col-lg-1"></div>
                    <label for="Responsibilities" class="col col-lg-2 mt-3 "
                      >담당업무</label
                    >
                    <input
                      type="text"
                      class="col-lg-3 form-control mt-2"
                      id="Responsibilities"
                      placeholder="담당업무"
                    />
                  </div>
                </div>
                <!--상담사 폼 경력 end-->

                <!--상담사 폼 자격증 start-->
                <div class="col-lg-12 row mb-2">
                  <label for="certificate" class="col col-lg-1 pr-1 mt-2"
                    >자격증</label
                  >

                  <base-button
                    type="secondary"
                    class="ni ni-fat-add p-1"
                  ></base-button>
                  <div class="col-lg-12 row teach py-3">
                    <label for="certificate_name" class="col col-lg-2 mt-2"
                      >자격증명</label
                    >
                    <input
                      type="text"
                      class="col-lg-3 form-control"
                      id="certificate_name"
                      placeholder="자격증명"
                    />
                    <div class="col-lg-1"></div>
                    <label for="cer_from" class="col col-lg-2 mt-2"
                      >발행처/기관</label
                    >
                    <input
                      type="text"
                      class="col-lg-3 form-control"
                      id="cer_from"
                      placeholder="발행처/기관"
                    />
                    <label for="cer_get" class="col col-lg-2 mt-3 "
                      >취득일</label
                    >
                    <input
                      type="date"
                      class="col-lg-3 form-control mt-2"
                      id="cer_get"
                    />
                    <div class="col-lg-5"></div>
                    <div class="col-lg-1"></div>
                    <label for="file" class="col col-lg-2 mt-3 "
                      >첨부파일</label
                    >
                    <input
                      type="file"
                      class="col-lg-9 form-control mt-2 form-control p-2"
                      id="file"
                      placeholder="담당업무"
                    />
                  </div>
                </div>
                <!--상담사 폼 자격증 end-->

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
export default {};
</script>
<style scoped>
input {
  height: 40px;
  border: 0;
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
.teach {
  border-radius: 10px;
  background-color: #ffdcbb;
}
input[type="file"] {
  border-radius: 0;
}
</style>

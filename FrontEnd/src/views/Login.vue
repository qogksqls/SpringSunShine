<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default"></div>
    <div class="container pt-lg-md mt-5">
      <div class="row justify-content-center mt-5">
        <div class="col-lg-6">
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
                  <b>로그인</b>
                </div>
              </h3>
              <form role="form">
                <div
                  class="row my-2 content row justify-content-center align-self-center"
                >
                  <label for="name" class="col col-lg-4 py-2 my-1"
                    >아이디</label
                  >

                  <input
                    v-model="id"
                    required
                    type="text"
                    placeholder="아이디를 적어주세요"
                    class="col-lg-8  form-control"
                    id="name"
                  />
                </div>
                <div
                  class="row mb-2 content row justify-content-center align-self-center"
                >
                  <label for="pw" class="col col-lg-4 py-2 my-1"
                    >비밀번호</label
                  >

                  <input
                    v-model="password"
                    required
                    type="password"
                    placeholder="비밀번호를 적어주세요"
                    class="col-lg-8  form-control"
                    id="pw"
                  />
                </div>
                <div class="text-right mt-3">
                  <base-checkbox>
                    아이디 저장
                  </base-checkbox>
                </div>
                <div class="text-center mt-3">
                  <router-link to="/signinSelect"
                    ><base-button type="primary" class="col-lg-3 m-4"
                      >회원가입</base-button
                    ></router-link
                  >
                  <base-button
                    type="primary"
                    class="col-lg-3 m-4"
                    @click="login"
                    >로그인</base-button
                  >
                </div>
              </form>
            </template>
          </card>

          <!-- 추후 비밀번호 찾기 넣을때 쓸꺼
          <div class="row mt-3">
            <div class="col-6">
              <a href="#" class="text-light">
                <small>비밀번호찾기</small>
              </a>
            </div>
          </div>
          -->
        </div>
      </div>
    </div>
  </section>
</template>
<script>
export default {
  data() {
    return {
      id: null,
      password: null,
    };
  },

  methods: {
    login() {
      let saveData = {};
      saveData.id = this.id;
      saveData.password = this.password;

      try {
        this.$axios
          .post(HOST + "/signin", JSON.stringify(saveData), {
            headers: {
              "Content-Type": `application/json`,
            },
          })
          .then((res) => {
            if (res.status === 200) {
              this.$store.commit("login", res.data);
              //다음페이지로 이동 -> 어디로 가나?
            }
          });
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
<style scoped>
html,
body {
  height: 100%;
}
.section {
  height: 100vh;
  justify-content: center;
  align-items: center;
}
</style>

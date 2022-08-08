<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default"></div>
    <div class="container pt-lg-md">
      <div class="row justify-content-center">
        <div class="col-lg-6">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <h4>
                <div class="text-left mb-3">
                  <b>봄날의 햇살에서 <br />어떤 서비스를 이용하고 싶으세요?</b>
                </div>
              </h4>
              <small class="text-muted"
                >원하는 회원가입 유형을 선택하세요.<br />의뢰인으로 가입 후에도
                전문가 등록이 가능합니다.</small
              >
              <br /><br />

              <div
                class="wrap_content row justify-content-center align-self-center"
              >
                <!--회원가입 폼 이름 작성 start-->

                <div class="row col-lg-12">
                  <div class="p-0 my-3 col-lg-12">
                    <small><strong>서비스를 의뢰하고 싶다면</strong></small>
                    <!--링크 넘어가기-->
                    <!-- <router-link :to="{
                      name: 'register',
                      query: { isTherapist: false}
                    }">
                      <div class="mt-2 p-3 parents_form go_to">
                        <i class="ni ni-check-bold"></i>
                        <small class="my-3 p-2"
                          ><strong>보호자로 가입</strong></small
                        >
                      </div>
                    </router-link> -->
                    <div @click="clickParent">
                      <div class="mt-2 p-3 parents_form go_to">
                        <i class="ni ni-check-bold"></i>
                        <small class="my-3 p-2"
                          ><strong>보호자로 가입</strong></small
                        >
                      </div>

                    </div>
                  </div>

                  <div class="p-0 my-3 col-lg-12">
                    <small><strong>상담사로 가입하고 싶다면</strong></small>
                    <!--링크 넘어가기-->
                    <!-- <router-link :to="{
                      name: 'register',
                      query: { isTherapist: true}
                    }">
                      <div class="mt-2 p-3 couns_form go_to">
                        <i class="ni ni-check-bold"></i>
                        <small class="my-3 p-2"
                          ><strong>상담사로 가입</strong></small
                        >
                      </div>
                    </router-link> -->
                    <div @click="clickTeacher">
                      <div class="mt-2 p-3 couns_form go_to">
                        <i class="ni ni-check-bold"></i>
                        <small class="my-3 p-2"
                          ><strong>상담사로 가입</strong></small
                        >
                      </div>

                    </div>
                  </div>
                </div>
                <!--회원가입 폼 이름 작성 end-->

                <!--회원가입 폼 학력 end-->
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  methods: {
    clickParent() {
      this.$router.push({name: 'register', params: { isTherapist: false }})
    },
    clickTeacher() {
      this.$router.push({name: 'register', params: { isTherapist: true }})
    }
  }
};
</script>

<style lang="css" scoped>
a{
    color: #000;
}
.go_to{
background-color: #fff;
border-radius: 5px;
border: 1px solid #dcdcdc;
}
.go_to:hover{
    background-color: #FDFFDC;
    border: 1px solid #ffdcb8;
}
</style>

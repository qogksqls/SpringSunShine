<template>
  <div>
    <!--상담사 폼 학력 start-->
    <!--상담사 폼 프로필사진 start-->
    <div class="col-lg-12 row m-0">
      <label for="profile_photo" class="col col-lg-2 pr-1 mt-2"
        >프로필사진</label
      >

      <label for="file" class="col col-lg-2 mt-3 "></label>
      <input
        type="file"
        class="col-lg-8 form-control mt-2 form-control p-2"
        id="file"
        placeholder="담당업무"
      />
    </div>
    <!--상담사 폼 자격증 end-->

    <div class="col-lg-12 row m-0">
      <label for="grade" class="col col-lg-2 mt-2">학력</label>

      <base-button type="secondary" class="ni ni-fat-add p-1"></base-button>
      <div class="col-lg-12 row teach py-3 m-0">
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
        <input type="date" class="col-lg-3 form-control mt-2" id="year" />
        <div class="col-lg-1"></div>
        <label for="year" class="col col-lg-2 mt-3 ">졸업</label>
        <input type="date" class="col-lg-3 form-control mt-2" id="year" />
      </div>
    </div>

    <!--상담사 폼 학력 end-->

    <!--상담사 폼 경력 start-->
    <div class="col-lg-12 row m-0">
      <label for="grade" class="col col-lg-2 mt-2">경력</label>

      <base-button type="secondary" class="ni ni-fat-add p-1"></base-button>
      <div class="col-lg-12 row teach py-3 m-0">
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
        <label for="inyear" class="col col-lg-2 mt-3 ">재직기간</label>
        <!--<input type="date" class="col-lg-3 form-control mt-2" id="year" />-->
        <div
          class="col-lg-9 input-daterange datepicker row align-items-center p-0 m-0"
        >
          <div class="col mt-2 p-0">
            <base-input addon-left-icon="ni ni-calendar-grid-58">
              <flat-picker
                slot-scope="{ focus, blur }"
                @on-open="focus"
                @on-close="blur"
                :config="{ allowInput: true, mode: 'range' }"
                class="form-control datepicker"
                v-model="dates.range"
              >
              </flat-picker>
            </base-input>
          </div>
        </div>
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
    <div class="col-lg-12 row m-0">
      <label for="certificate" class="col col-lg-2 pr-1 mt-2">자격증</label>

      <base-button type="secondary" class="ni ni-fat-add p-1"></base-button>
      <div class="col-lg-12 row teach py-3 m-0">
        <label for="certificate_name" class="col col-lg-2 mt-2">자격증명</label>
        <input
          type="text"
          class="col-lg-3 form-control"
          id="certificate_name"
          placeholder="자격증명"
        />
        <div class="col-lg-1"></div>
        <label for="cer_from" class="col col-lg-2 mt-2">발행처/기관</label>
        <input
          type="text"
          class="col-lg-3 form-control"
          id="cer_from"
          placeholder="발행처/기관"
        />
        <label for="cer_get" class="col col-lg-2 mt-3 ">취득일</label>
        <input type="date" class="col-lg-3 form-control mt-2" id="cer_get" />
        <div class="col-lg-5"></div>
        <div class="col-lg-1"></div>
        <label for="file" class="col col-lg-2 mt-3 ">첨부파일</label>
        <input
          type="file"
          class="col-lg-9 form-control mt-2 form-control p-2"
          id="file"
          placeholder="담당업무"
        />
      </div>
    </div>
    <!--상담사 폼 자격증 end-->
  </div>
</template>

<script>
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";
export default {
  components: {
    flatPicker,
  },
  data() {
    return {
      dates: {
        simple: "2022-08-05",
      },
      // config: {
      //   altFormat: "M j, Y",
      //   altInput: true,
      //   dateFormat: "Y-m-d",
      //   locale: Hindi, // locale for this instance only
      // },
    };
  },
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
.teach {
  border-radius: 10px;
  background-color: #ffdcbb;
}
input[type="file"] {
  line-height: 1em;
}
.input-group-text {
  border-radius: 15px 0 0 15px;
}
.form-group {
  margin-bottom: 0;
}
</style>

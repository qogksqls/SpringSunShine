<template>
  <div>
    <!--상담사 폼 학력 start-->
    <!-- 상담사 전문 분야 -->
    <hr />
    <div class="col-lg-12 row m-0">
      <label for="expertise_no" class="col col-lg-2 pr-1 mt-2">전문 분야</label>

      <input
        type="checkbox"
        id="자폐증"
        :value=1
        v-model="datas.expertise_no"
        @change="counselor"
      />
      <label for="자폐증">자폐증</label>
      <label for="file" class="col col-lg-2 mt-3 "></label>
      <input
        type="checkbox"
        id="아스퍼거"
        :value=2
        v-model="datas.expertise_no"
        @change="counselor"
      />
      <label for="아스퍼거 증후군">아스퍼거 증후군</label>
      <label for="file" class="col col-lg-2 mt-3 "></label>
      <input
        type="checkbox"
        id="전반적 발달 장애"
        :value=3
        v-model="datas.expertise_no"
        @change="counselor"
      />
      <label for="전반적 발달 장애">전반적 발달 장애</label>
      <label for="file" class="col col-lg-2 mt-3 "></label>
      <input
        type="checkbox"
        id="레트 증후군"
        :value=4
        v-model="datas.expertise_no"
        @change="counselor"
      />
      <label for="레트 증후군">레트 증후군</label>
      <label for="file" class="col col-lg-2 mt-3 "></label>
      <input
        type="checkbox"
        id="아동기 붕괴성 장애"
        :value=5
        v-model="datas.expertise_no"
        @change="counselor"
      />
      <label for="아동기 붕괴성 장애">아동기 붕괴성 장애</label>
    </div>
    <!--상담사 폼 프로필사진 start-->
    <hr />
    <div class="col-lg-12 row m-0">
      <label for="profile_photo" class="col col-lg-2 pr-1 mt-2"
        >프로필사진</label
      >
      <!-- <img v-if="URL.createObjectURL(img_src)" :src="img_src" width="128" height="128"> -->
      <label for="file" class="col col-lg-2 mt-3 "></label>
      <form>
        <input
          type="file"
          class="col-lg-8 form-control mt-2 form-control p-2"
          id="photo"
          name="photo"
          placeholder="프로필사진"
          @change="addProfilePicture"
        />
      </form>
    </div>
    <!--상담사 폼 학력 start-->
    <hr />
    <div class="col-lg-12 row m-0">
      <label for="grade" class="col col-lg-2 mt-2">학력</label>

      <base-button
        type="secondary"
        class="ni ni-fat-add p-1"
        @click="학력추가 = true"
      ></base-button>
      <div class="col-lg-12 row teach py-3 m-0" v-if="학력추가 == true">
        <label for="school" class="col col-lg-2 mt-2">학교명</label>
        <input
          type="text"
          class="col-lg-3 form-control"
          id="school"
          placeholder="학교명"
          v-model="university"
        />
        <div class="col-lg-1"></div>
        <label for="major" class="col col-lg-2 mt-2">전공</label>
        <input
          type="text"
          class="col-lg-3 form-control"
          id="major"
          placeholder="전공"
          v-model="major"
        />
        <label for="inyear" class="col col-lg-2 mt-3 ">입학</label>
        <input
          type="date"
          class="col-lg-3 form-control mt-2"
          id="year"
          v-model="admission"
        />
        <div class="col-lg-1"></div>
        <label for="year" class="col col-lg-2 mt-3 ">졸업</label>
        <input
          type="date"
          class="col-lg-3 form-control mt-2"
          id="year"
          v-model="graduation"
        />

        <p>
          <button @click="addAcademicCareer">완료</button>
        </p>
        <p>
          <button @click="학력추가 = false">취소</button>
        </p>
      </div>
    </div>
    <hr />

    <div v-for="(academicCareer, i) in datas.academicCareers" :key="i">
      <div class="col-lg-12 row teach py-3 m-0">
        <label for="school" class="col col-lg-2 mt-2"
          >학교명: {{ academicCareer["name"] }}</label
        >
        <div class="col-lg-1"></div>
        <label for="major" class="col col-lg-2 mt-2"
          >전공: {{ academicCareer["major"] }}</label
        >
        <label for="inyear" class="col col-lg-2 mt-3 "
          >입학일: {{ academicCareer["admin"] }}</label
        >
        <div class="col-lg-1"></div>
        <label for="year" class="col col-lg-2 mt-3 "
          >졸업일: {{ academicCareer["gradu"] }}</label
        >
        <p>
          <button @click="deleteAcademicCareer(i)">삭제</button>
        </p>
      </div>
      <hr />
    </div>
    <!--상담사 폼 학력 end-->

    <!--상담사 폼 경력 start-->
    <div class="col-lg-12 row m-0">
      <label for="grade" class="col col-lg-2 mt-2">경력</label>
      <base-button
        type="secondary"
        class="ni ni-fat-add p-1"
        @click="경력추가 = true"
      ></base-button>

      <div class="col-lg-12 row teach py-3 m-0" v-if="경력추가 == true">
        <label for="job" class="col col-lg-2 mt-2">회사명</label>
        <input
          type="text"
          class="col-lg-3 form-control"
          id="job"
          placeholder="회사명"
          v-model="company"
        />
        <div class="col-lg-1"></div>
        <label for="rank" class="col col-lg-2 mt-2">직급</label>
        <input
          type="text"
          class="col-lg-3 form-control"
          id="rank"
          placeholder="직급"
          v-model="position"
        />
        <label for="inyear" class="col col-lg-2 mt-3 ">재직기간</label>
        <!-- <input type="date" class="col-lg-3 form-control mt-2" id="year" /> -->
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
          v-model="part"
        />
        <p>
          <button @click="addCareer">완료</button>
        </p>
        <p>
          <button @click="경력추가 = false">취소</button>
        </p>
      </div>
      <hr />
    </div>
    <hr />

    <div v-for="(career, idx) in datas.careers" :key="idx">
      <div class="col-lg-12 row teach py-3 m-0">
        <label for="school" class="col col-lg-2 mt-2"
          >회사명: {{ career["name"] }}</label
        >
        <div class="col-lg-1"></div>
        <label for="major" class="col col-lg-2 mt-2"
          >직급: {{ career["level"] }}</label
        >
        <label for="inyear" class="col col-lg-2 mt-3 "
          >재직기간: {{ career["date"].range }}</label
        >
        <div class="col-lg-1"></div>
        <label for="year" class="col col-lg-2 mt-3 "
          >담당업무: {{ career["role"] }}</label
        >
        <p>
          <button @click="deleteCareer(idx)">삭제</button>
        </p>
      </div>
      <hr />
    </div>
    <!--상담사 폼 경력 end-->

    <!--상담사 폼 자격증 start-->
    <div class="col-lg-12 row m-0">
      <label for="certificate" class="col col-lg-2 pr-1 mt-2">자격증</label>
      <base-button
        type="secondary"
        class="ni ni-fat-add p-1"
        @click="자격증추가 = true"
      ></base-button>

      <div class="col-lg-12 row teach py-3 m-0" v-if="자격증추가 == true">
        <label for="certificate_name" class="col col-lg-2 mt-2">자격증명</label>
        <input
          type="text"
          class="col-lg-3 form-control"
          id="certificate_name"
          placeholder="자격증명"
          v-model="licence_name"
        />
        <div class="col-lg-1"></div>
        <label for="cer_from" class="col col-lg-2 mt-2">발행처/기관</label>
        <input
          type="text"
          class="col-lg-3 form-control"
          id="cer_from"
          placeholder="발행처/기관"
          v-model="licence_publication"
        />
        <label for="cer_get" class="col col-lg-2 mt-3 ">취득일</label>
        <input
          type="date"
          class="col-lg-3 form-control mt-2"
          id="cer_get"
          v-model="acquisition_date"
        />

        <div class="col-lg-5"></div>
        <div class="col-lg-1"></div>
        <label for="file" class="col col-lg-2 mt-3 ">첨부파일</label>
        <input
          type="file"
          class="ex_file col-lg-9 form-control mt-2 form-control p-2"
          id="file"
          placeholder="자격증파일"
          @change="addLicenceFile"
        />
        <p>
          <button @click="addLicence">완료</button>
        </p>
        <p>
          <button @click="자격증추가 = false">취소</button>
        </p>
      </div>
      <hr />
    </div>
    <hr />

    <div v-for="(licence, i) in datas.licences" :key="i">
      <div class="col-lg-12 row teach py-3 m-0">
        <label for="school" class="col col-lg-2 mt-2"
          >자격증명: {{ licence["name"] }}</label
        >
        <div class="col-lg-1"></div>
        <label for="major" class="col col-lg-2 mt-2"
          >발행처/기관: {{ licence["place"] }}</label
        >
        <label for="inyear" class="col col-lg-2 mt-3 "
          >취득일: {{ licence["date"] }}</label
        >
        <div class="col-lg-1"></div>
        <label for="year" class="col col-lg-2 mt-3 "
          >첨부파일: {{ licence["file"] }}</label
        >
        <p>
          <button @click="deleteLicence(i)">삭제</button>
        </p>
      </div>
      <hr />
    </div>
    <!--상담사 폼 자격증 end-->
  </div>
</template>

<script>
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";

var today = new Date();
var year = today.getFullYear();
var month = ("0" + (today.getMonth() + 1)).slice(-2);
var day = ("0" + today.getDate()).slice(-2);
var dateString = year + "-" + month + "-" + day;

export default {
  components: {
    flatPicker,
  },
  data() {
    return {
      dates: {
        simple: dateString,
      },
      datas: {
        profile_url: "",
        expertise_no: [],
        academicCareers: [],
        careers: [],
        licences: [],
        thera_intro: "",
      },
      file_name: "파일을 선택하세요.",
      file: "",
      img_src: '',

      학력추가: false,
      경력추가: false,
      자격증추가: false,

      university: "",
      major: "",
      admission: "",
      graduation: "",

      company: "",
      position: "",
      part: "",

      licence_name: "",
      licence_publication: "",
      acquisition_date: "",
      licence_file_name: "",
      licence_file_url: "",
    };
  },
  methods: {
    addProfilePicture(e) {
      var photoFile = document.getElementById("photo");
      this.datas.profile_url = photoFile
      this.counselor()
    },
    addLicenceFile(e) {
      let file = e.target.files[0];
      let name = file.name;
      this.licence_file_name = file.name;
      this.file = file;
      if(name.endsWith('.pdf') || name.endsWith('.docx') || 
        name.endsWith('.hwp') || name.endsWith('.md') || name.endsWith('.ppt'))
        this.licence_file_url = URL.createObjectURL(file);
      else
        alert("pdf, docx, hwp의 파일로 올려주세요.")
        this.licence_file_url = ""
    },
    addAcademicCareer() {
      if (this.university && this.major && this.admission && this.graduation) {
        this.datas.academicCareers.push({
          "name": this.university,
          "major": this.major,
          "admin": this.admission,
          "gradu": this.graduation
        });
        this.counselor()
        this.university = ""
        this.major = ""
        this.admission = ""
        this.graduation = ""
        this.학력추가 = false;
    } else {
        alert("모두 정확하게 입력하시오.");
      }
    },
    deleteAcademicCareer(index) {
      this.datas.academicCareers.splice(index, 1)
      this.counselor()
    },
    addCareer() {
      if (this.company && this.position && this.dates && this.part) {
        this.datas.careers.push({
          "name": this.company,
          "level": this.position,
          "date": this.dates.range,
          "role": this.part,
      });
        this.counselor()
        this.company = ""
        this.position = ""
        this.dates = ""
        this.part = ""
        this.경력추가 = false;
      } else {
        alert("모두 정확하게 입력하시오.");
      }
    },
    deleteCareer(index) {
      this.datas.careers.splice(index, 1)
      this.counselor()
    },
    addLicence() {
      if (
        this.licence_name &&
        this.licence_publication &&
        this.acquisition_date &&
        this.licence_file_name
      ) {
        this.datas.licences.push({
          "name": this.licence_name,
          "place": this.licence_publication,
          "date": this.acquisition_date,
          "file": this.licence_file_name,
        });
        this.counselor()
        this.licence_name = "",
        this.licence_publication = "",
        this.acquisition_date = "",
        this.licence_file_name = "",
        this.자격증추가 = false;
      } else {
        alert("모두 정확하게 입력하시오.");
      }
    },
    deleteLicence(index) {
      this.datas.licences.splice(index, 1)
      this.counselor()
    },
    counselor() {
      this.$emit("counselor", this.datas);
    },
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

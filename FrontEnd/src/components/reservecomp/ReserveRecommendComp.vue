<template>
  <div>
    <v-app>
      <div id="dataSheet">
        <v-data-table
          :headers="headers"
          :items="desserts"
          :sort-by="[]"
          :sort-desc="[]"
          multi-sort
          class="elevation-1"
          style='border-radius: 3% / 8%;'
        ></v-data-table>
      </div>
    </v-app>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        headers: [
          {
            text: '이름',
            align: 'center',
            sortable: true,
            value: 'name',
          },
          { text: '전공', value: 'major' },
          { text: '경력(년)', value: 'career' },
          { text: '전문분야', value: 'Specialty' },
          { text: '성별', value: 'gender' },
        ],
        desserts: [
          {
            name: '박광철',
            major: '아동심리학',
            career: 10,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '김덕배',
            major: '놀이치료학',
            career: 8,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '서팔광',
            major: '심리학',
            career: 6,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '김덕협',
            major: '정신감정학',
            career: 12,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '마석대',
            major: '정신치료사',
            career: 22,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '김억두',
            major: '아동보호학',
            career: 24,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '왕대협',
            major: '아동심리학',
            career: 3,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '여춘팔',
            major: '아동심리학',
            career: 1,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '홍혁팔',
            major: '아동심리학',
            career: 9,
            Specialty: '놀이치료',
            gender: '남',
          },
          {
            name: '마춘동',
            major: '아동심리학',
            career: 14,
            Specialty: '놀이치료',
            gender: '남',
          },
        ],
      }
    },
  }
</script>

<style>
#dataSheet {
  background-color: bisque;
  padding: 3%;
  margin-top: 5%;
  margin-left: 3%;
  margin-right: 3%;
  border-radius: 5% / 15%;
}


</style>
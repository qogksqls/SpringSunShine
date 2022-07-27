<template>
  <div id="baseDiv">
    <img id='profileImage' src="../../assets/doctor.jpg">
    <div id='story'>
      약력
    </div>
    <div id="profile">

      <br>
      <h1 class="profileText">id {{id}}</h1>
      <br>
      <h1 class="profileText">password {{password}}</h1>
      <br>
      <h1 class="profileText">name {{name}}</h1>
      <br>
      <h1 class="profileText">age {{age}}</h1>
      <br>
      <h1 class="profileText">email {{email}}</h1>
      <br>
      <h1 class="profileText">call {{call}}</h1>
      <br>
      <h1 class="profileText">adress {{adress}}</h1>

      <button class="profileBtn">정보수정</button>
      <button class="profileBtn">회원탈퇴</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: '아이디',
      password: '비밀번호',
      name: '이름',
      age: '나이',
      email: '이메일',
      call: '연락처',
      adress: '주소',
    }
  }
}
</script>

<style>
#baseDiv {
  margin: auto;
  margin-top: 2vh;
  width: 90vh;
  height: 90vh;
  background-color: beige;
  border-radius: 5%;
}

#profileImage {
  height: 25%;
  width: auto;
  margin-top: 5%;
  margin-left: 5%;
  border-radius: 50%;
}

#story {
  margin-top: 3%;
  margin-left: 2.5%;
  border-radius: 5%;
  height: 65%;
  width: 30%;
  background-color: white;
}

#profile {
  position: relative;
  background-color: white;
  bottom: 95%;
  left: 35%;
  height: 95%;
  width: 62.5%;
  border-radius: 5%;
  font-family: 'GangwonEdu_OTFBoldA';
}

.profileText {
  margin-top: 8%;
  margin-left: 5%;
}

.profileBtn {
  margin-top: 8%;
  margin-left: 5%;
}
</style>
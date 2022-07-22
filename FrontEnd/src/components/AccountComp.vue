<template>
  <div id="bigDiv">
    <v-app>
      <v-form
        ref="form"
        v-model="valid"
        lazy-validation
      >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <v-text-field
          v-model="userId"
          :counter="10"
          :rules="idRules"
          label="Id"
          required
        ></v-text-field>

        <v-text-field
          v-model="userName"
          :counter="10"
          :rules="nameRules"
          label="Name"
          required
        ></v-text-field>

        <v-text-field
          v-model="userPassword"
          :counter="10"
          :rules="passwordRules"
          label="Password"
          required
        ></v-text-field>

          <v-select
            v-model="userPosition"
            :items="items"
            :rules="[v => !!v || 'Item is required']"
            label="Position"
            required
          ></v-select>

        <v-text-field
          v-model="userDepartment"
          :counter="10"
          :rules="[v => !!v || 'Department is required',]"
          label="Department"
          required
        ></v-text-field>



        <v-btn
          :disabled="!valid"
          color="success"
          class="mr-4"
          @click="sendData"
        >
          Account
        </v-btn>

        <v-btn
          color="error"
          class="mr-4"
          @click="reset"
        >
          Reset Form
        </v-btn>

      </v-form>
    </v-app>
  </div>
</template>

<script>
import axios from "axios"
export default {
  data() {
    return {
      host: "http://localhost:8181/server/user",
      userId: null,
      userName: null,
      userPassword: null,
      userPosition: null,
      userDepartment: null,
      valid: true,

      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],

      idRules: [
        v => !!v || 'Id is required',
        v => (v && v.length <= 10) || 'Id must be less than 10 characters',
      ],

      passwordRules: [
        v => !!v || 'Password is required',
        v => (v && v.length >= 5) || 'Password must be less than 10 characters',
      ],

      items: [
        'Pro',
        'Student',
        'Coach',
      ],
    }
  },
  methods: {
      // validate () {
      //   this.$refs.form.validate()
      // },
      reset () {
        this.$refs.form.reset()
      },
      resetValidation () {
        this.$refs.form.resetValidation()
      },
      sendData () {
        this.$refs.form.validate()
        axios
        .post(this.host,
          {
            'userid' : this.userId,
            'name' : this.userName,
            'password' : this.userPassword,
            'position' : this.userPosition,
            'department' : this.userDepartment,
          },
        )
        .then( res => {
          if (this.$refs.form.validate()) {
            if (res.status === 200) {
              console.log('success')
              console.log('res')
            } else {
              console.log(res)
            }
          } else {
            console.log('unvalid')
          }
        })
        .catch( err => {
          console.log(err)
        })
        }
      }
}
</script>

<style scoped>
#bigDiv {
  display: flex;
  width: 100%;
  justify-content: center;
}
</style>
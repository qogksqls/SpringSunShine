<template>
  <div id='bigDiv'>
    <v-app>
      <v-form
        ref="form"
        v-model="valid"
        lazy-validation
      >
        <v-text-field
          v-model="loginId"
          :counter="10"
          :rules="idRules"
          label="Id"
          required
        ></v-text-field>

        <v-text-field
          v-model="loginPassword"
          :rules="passwordRules"
          label="Password"
          required
        ></v-text-field>


        <v-btn
          :disabled="!valid"
          color="success"
          class="mr-4"
          @click="Login"
        >
          Login
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
import axios from 'axios'
  export default {
    data () {
      return {
      host: "http://localhost:8181/server/user",
      valid: true,
      loginId: '',
      idRules: [
        v => !!v || 'Name is required',
      ],

      loginPassword: '',
      passwordRules: [
        v => !!v || 'E-mail is required',
      ],
      }

    },

    methods: {
      Login () {
        this.$refs.form.validate()
        if (this.$refs.form.validate()){
          axios
          .post(this.host + '/login',
            {
              userid: this.loginId,
              password: this.loginPassword,
            },
            {
              headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Methods': 'GET',
                  }
            })
          .then( res => {
            if (res.status === 200) {
                console.log('success')
                this.$store.state.userStore.token = res.data.token
                this.$store.state.userStore.userId = res.data.userId
                this.$store.state.userStore.name = res.data.name
                this.$store.state.userStore.position = res.data.position
                this.$store.state.userStore.department = res.data.department
                this.$store.state.userStore.login = true
                this.$router.push('home')
              } else {
                console.log(res)
              }
          })
          .catch( err => {
            console.log(err)
          })
        }
      },
      reset () {
        this.$refs.form.reset()
      },
      resetValidation () {
        this.$refs.form.resetValidation()
      },
    },
  }
</script>

<style scoped>
#bigDiv {
  display: flex;
  width: 100%;
  justify-content: center;
}
</style>
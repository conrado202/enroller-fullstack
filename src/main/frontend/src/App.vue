<template>
  <div id="app">
    <h1>
      <img src="./assets/logo.svg" alt="Enroller" class="logo">
      System do zapisów na zajęcia
    </h1>
    <div v-if="authenticatedUsername">
      <h2>Witaj {{ authenticatedUsername }}!
        <a @click="logout()" class="float-right  button-outline button">Wyloguj</a>
      </h2>
      <meetings-page :username="authenticatedUsername"></meetings-page>
    </div>
    <div v-else>
<button :class="registering ? 'button-outline' : ''" @click="registering = false">Loguję się</button>
<button :class="!registering ? 'button-outline' : ''" @click="registering = true">Rejestruję się</button>

      <div v-if="errorMessage" class="alert-warning">{{errorMessage}}</div>

      <login-form v-if="!registering" @login="login($event)"></login-form>
      <login-form v-if="registering" @login="register($event)"
                  button-label="Zarejestruj sie"></login-form>
    </div>
  </div>
</template>

<script>
    import "milligram";
    import LoginForm from "./LoginForm";
    import MeetingsPage from "./meetings/MeetingsPage";

 

    export default {
        components: {LoginForm, MeetingsPage},
        data() {
            return {
                authenticatedUsername: "",
                registering: false,
                errorMessage: ''
            };
        },
        methods: {
            login(user) {
                this.authenticatedUsername = user.login;
            },
            register(user) {
                this.errorMessage = '';
                this.$http.post('participants', user)
                    .then(response => {
                        this.registering = false;
                    })
                    .catch(response => {
                        // this.errorMessage = "Nie udalo sie zarejestrowac konta";
                        this.errorMessage = response.bodyText;
                    });
            },
            logout() {
                this.authenticatedUsername = '';
            }

        }
    };
</script>

<style>
  #app {
    max-width: 1000px;
    margin: 0 auto;
  }

  .logo {
    vertical-align: middle;
  }

  .alert-warning {
      border: 3px red dotted;
      padding: 3px;
      background: orange;

  }
</style>


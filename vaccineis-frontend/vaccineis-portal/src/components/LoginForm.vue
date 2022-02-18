<template>
<form @submit.prevent="login">
    <div class="modal-body text-start">
        <div class="form-group">
            <input v-model="authenticationData.email" type="text" class="form-control" placeholder="Имејл" required/>
            <div class="invalid-feedback">Невалидан имејл.</div>
        </div>
        <div class="form-group">
            <input v-model="authenticationData.sifra" type="password" class="form-control" placeholder="Лозинка" required/>
            <div class="invalid-feedback">Невалидна шифра.</div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Пријави се</button>
    </div>
</form>
</template>

<script>
import AuthenticationService from "@/service/AuthenticationService.js";
import xmljs from "xml-js";
import axios from "axios"

export default {
    name: "LoginForm",

    data() {
        return {
            authenticationData: {
                email: "",
                sifra: "",
            },
        };
    },

    methods: {

        login() {
            let data = "<AuthenticationRequestDTO>" + xmljs.json2xml(this.authenticationData, {compact: true, spaces: 4}) + "</AuthenticationRequestDTO>";
            AuthenticationService.login(data)
                .then(response => { 
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4})); 

                    let user = data["AuthenticationResponseDTO"];
                    this.toast("Добродошао " + user.ime._text + " " + user.prezime._text + "!", "success")    

                    user.rola = user.rola._text;

                    let token = data["AuthenticationResponseDTO"]["jwt"]["_text"]
                    console.log(token);
                    localStorage.setItem('token', token);
                    localStorage.setItem('user', JSON.stringify(user));
                    axios.defaults.headers.common['Authorization'] = "Bearer " + token;

                    this.$store.dispatch('loadAuthorizedUser');
                    this.$router.push("/");
                })
                .catch((error) => { console.log(error) })
        },

        toast(message, type) {
            this.$toasted.show(message, {
                type: type,
                theme: "toasted-primary",
                position: "top-center",
                duration: 3000,
            });
        },
    },
};
</script>

<style scoped>
form {
  margin-top: 20px;
}
input {
  outline: none;
  border-radius: 5px;
  border: none;
  background: #d3d3d3;
  font-family: Tahoma;
}
label {
  font-family: Tahoma;
}
.btn {
  margin: auto;
  display: block;
}
</style>
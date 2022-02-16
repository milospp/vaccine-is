<template>
<form @submit.prevent="registration">
    <div class="modal-body text-start">
        <div class="form-group">
            <input v-model="user.ime" type="text" class="form-control" placeholder="Име"/>
            <div class="invalid-feedback">Невалидно име.</div>
        </div>
        <div class="form-group">
            <input v-model="user.prezime" type="text" class="form-control" placeholder="Презиме"/>
            <div class="invalid-feedback">Невалидно презиме.</div>
        </div>
        <div class="form-group">
            <input v-model="user.jmbg" type="text" class="form-control" placeholder="Јмбг / ЕБС"/>
            <div class="invalid-feedback">Невалидан јмбг.</div>
        </div>
        <div class="form-group">
            <input v-model="user.email" type="text" class="form-control" placeholder="Имејл"/>
            <div class="invalid-feedback">Невалидан имејл.</div>
        </div>
        <div class="form-group">
            <input v-model="user.sifra" type="password" class="form-control" placeholder="Лозинка"/>
            <div class="invalid-feedback">Невалидна лозинка.</div>
        </div>
    </div>

    <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Региструј се</button>
    </div>
</form>
</template>

<script>
import AuthenticationService from "@/service/AuthenticationService.js";
import xmljs from "xml-js";


export default {
    name: "RegistrationForm",

    data() {
        return {
            user: {
                ime: "",
                prezime: "",
                jmbg: "",
                email: "",
                sifra: ""
            },
        };
    },

    methods: {
        registration() {
            console.log(xmljs);

            let data = "<UserRegistrationDTO>" + xmljs.json2xml(this.user, {compact: true, spaces: 4}) + "</UserRegistrationDTO>";

            console.log(data);
            AuthenticationService.register(data)
                .then(response => { 
                    console.log(xmljs.xml2json(response));
                })
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
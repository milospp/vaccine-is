<template>
<form @submit.prevent="registration" id="registration-form">
    <div class="modal-body text-start">
        <div class="form-group">
            <input v-model="user.ime" type="text" class="form-control" placeholder="Име" required/>
            <div class="invalid-feedback">Невалидно име.</div>
        </div>
        <div class="form-group">
            <input v-model="user.prezime" type="text" class="form-control" placeholder="Презиме" required/>
            <div class="invalid-feedback">Невалидно презиме.</div>
        </div>
        <div class="form-group">
            <input v-model="user.email" id="email" type="text" class="form-control" placeholder="Имејл" required/>
            <div class="invalid-feedback">Невалидан имејл.</div>
        </div>
        <div class="form-group">
            <input v-model="user.sifra" type="password" class="form-control" placeholder="Лозинка" required/>
            <div class="invalid-feedback">Невалидна лозинка.</div>
        </div>
    </div>

    <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Региструј се</button>
    </div>
</form>
</template>

<script>
import { mapState } from "vuex";
import AuthenticationService from "@/service/AuthenticationService.js";
import xmljs from "xml-js";


export default {
    name: "RegistrationForm",

    computed: {
        ...mapState([
            'emailRegex'
        ])
    },

    data() {
        return {
            user: {
                ime: "",
                prezime: "",
                email: "",
                sifra: "",
                rola: 'GRADJANIN'
            },
        };
    },

    methods: {
        registration() {
            this.resetInvalidStates();

            if (!this.validateForm())
                return;

            let data = "<korisnik xmlns='http://www.ftn.uns.ac.rs/korisnik'>" + xmljs.json2xml(this.user, {compact: true, spaces: 4}) + "</korisnik>";
            AuthenticationService.register(data)
                .then(() => { 
                    this.toast("Успешно сте се регистровали!", "success");
                    this.$router.push({ name: "Login" })
                })
                .catch((error) => {
                    console.log(error);
                    this.toast("Невалидан унос података!", "error")    
                })
        },

        resetInvalidStates() {
            let elements = document.querySelectorAll(".form-group input");
            elements.forEach(el => el.classList.remove("is-invalid"));

            let form = document.getElementById("registration-form");
            form.classList.remove("was_validated");
        },

        validateForm() {
            let form = document.getElementById("registration-form");
            form.classList.add("was_validated");

            if (!form.checkValidity())
                return false;

            // eslint-disable-next-line
            if (!this.emailRegex.test(this.user.email)) {
                this.setInputInvalid(form, "email");
                return false;
            }

            return true;
        },

        setInputInvalid(form, inputId) {
            let inputEl = document.getElementById(inputId);
            inputEl.classList.add("is-invalid");
            form.checkValidity();
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
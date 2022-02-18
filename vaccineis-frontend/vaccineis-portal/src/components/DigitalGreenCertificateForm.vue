
<template>
<form id="digital-green-certificate-form" @submit.prevent="confirm">
    <div class="form-group">
        <label for="ime">Име</label>
        <input v-model="zahtev.podnosilac.ime._text" type="text" id="ime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="prezime">Презиме</label>
        <input v-model="zahtev.podnosilac.prezime._text" type="text" id="prezime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.lastName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="datumRodjenja">Датум рођења</label>
        <input v-model="zahtev.podnosilac.datumRodjenja._text" type="date" id="datumRodjenja" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>    
    <div class="form-group">
        <label for="jmbg">ЈМБГ</label>
        <input v-model="zahtev.podnosilac.jmbg._text" type="text" id="jmbg" class="form-control" required> 
        <div class="invalid-feedback">Невалидан формат</div>
    </div>
    <div class="form-group">
        <label>Пол?</label>
        <div class="checkbox-from-control">
            <input v-model="zahtev.podnosilac.pol._text" type="radio" name="specificVaccinationDateChecked" value="Мушко" class="" id="pol1" title=""><span>Мушко</span>
        </div>
        <div class="checkbox-from-control">
            <input v-model="zahtev.podnosilac.pol._text" type="radio" name="specificVaccinationDateChecked" value="Женско" class="" id="pol2" title=""><span>Женско</span>
        </div>
        <div class="invalid-feedback">Невалидан формат</div>
    </div>
    <br>
    <div class="form-group">
        <label for="brojPasosa">Број пасоша</label>
        <input minlength="8" v-model="zahtev.podnosilac.brojPasosa._text" type="text" id="brojPasosa" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.contactPhone" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="razlog">Разлог за подношење захтева</label>
        <editor id="razlog" v-model="value" @change="onChange" theme="snow" :options="options"/>
<!--        <input v-model="zahtev.razlog" type="text" id="razlog" class="form-control" required>-->
<!--        <span class="validation-error" data-bind="validationMessage: patientInformation.email" style="display: none;"></span>-->
    </div>
    <div class="form-group">
        <label for="lokacija">Локација</label>
        <input v-model="zahtev.mjestoDatum.lokacija" type="text" id="lokacija" class="form-control" required> 
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
    <br>
    <div class="bottom">
        <button class="button secondary" href="#" data-bind="click: save">Поднеси захтев</button>
    </div>
</form>
</template>

<script>
import DigitalniZeleniSertifikatService from "@/service/DigitalniZeleniSertifikatService.js";
import { mapState } from "vuex";
import xmljs from "xml-js";
import '@morioh/v-quill-editor/dist/editor.css';
import Editor from '@morioh/v-quill-editor';
import Vue from "vue";

Vue.use(Editor);

export default {
    name: "DigitalGreenCertificateForm",

    computed: {
        ...mapState([
            'user',
            'jmbgRegex',
            'brojFiksnogRegex',
            'brojMobilnogRegex'
        ])
    },

    data() {
        return {
            zahtev: {
                podnosilac: {
                    ime: {
                        "_attributes": {
                            "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                        },
                        "_text": ""
                    },
                    prezime: {
                        "_attributes": {
                            "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                        },
                        "_text": ""
                    },
                    pol: {
                        "_attributes": {
                            "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                        },
                        "_text": ""
                    },
                    datumRodjenja: {
                        "_attributes": {
                            "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                        },
                        "_text": ""
                    },
                    jmbg: {
                        "_attributes": {
                            "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                        },
                        "_text": ""
                    },
                    brojPasosa: {
                        "_attributes": {
                            "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                        },
                        "_text": ""
                    }
                },
                razlog: "",
                mjestoDatum: {
                    lokacija: "",
                    datum: ""
                }
            },
          value: '',
          options: {
              modules: {
                'toolbar': [
                    [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
                    ['bold', 'italic', 'underline', 'strike'],
                    [{ 'color': [] }, { 'background': [] }],
                    [{ 'script': 'super' }, { 'script': 'sub' }],
                    [{ 'header': '1' }, { 'header': '2' }, 'blockquote', 'code-block'],
                    [{ 'list': 'ordered' }, { 'list': 'bullet' }]
                ],
              }
          }
        };
    },

    created() {
        this.zahtev.podnosilac.ime._text = this.user.ime;
        this.zahtev.podnosilac.prezime._text = this.user.prezime;
    },

    methods: {
        confirm() {
            this.resetInvalidStates();

            if (!this.validateForm())
                return;

            this.zahtev.mjestoDatum.datum = new Date().toISOString().slice(0, 10);

            let data = "<zahtjev xmlns='http://www.ftn.uns.ac.rs/zahtjevSertifikata'>" + xmljs.json2xml(this.zahtev, {compact: true, spaces: 4}) + "</zahtjev>";
            DigitalniZeleniSertifikatService.createZahtev(data)
                .then(() => { this.toast("Успешно сте поднели захтев за дигитални зелени сертификат", "success"); })
                .catch(error => { this.toast("Невалидан унос података! Покушајте поново.", "error"); console.log(error.response.data.message) });
        },

        resetInvalidStates() {
            let elements = document.querySelectorAll(".form-group input");
            elements.forEach(el => el.classList.remove("is-invalid"));

            let form = document.getElementById("digital-green-certificate-form");
            form.classList.remove("was_validated");
        },


        validateForm() {
            let form = document.getElementById("digital-green-certificate-form");
            form.classList.add("was_validated");

            if (!form.checkValidity())
                return false;

            if (!this.jmbgRegex.test(this.zahtev.podnosilac.jmbg._text)) {
                this.setInputInvalid(form, "jmbg");
                return false;
            }

            if (this.zahtev.podnosilac.pol._text !== "Мушко" && this.zahtev.podnosilac.pol._text !== "Женско") {
                this.setInputInvalid(form, "pol");
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

        onChange(html, text) {
          console.log(html.length, text.length);
          let temp = html.replaceAll('&', '&amp;').replaceAll('<', '&lt;').replaceAll('>', '&gt;').replaceAll('"', '&quot;').replaceAll("'", '&#039;');
          this.zahtev.razlog = temp;
          console.log(text);
          console.log(html);
          console.log(temp);
        },

    },
};
</script>


<style scoped>
form {
    margin: 20px;
}

.button.secondary {
    transition: .3s !important;
    background-color: #017932 !important;
    border: none !important;
    font-family: inherit;
    width: 100%;
    
}

 .button {
    display: block;
    box-sizing: border-box;
    padding-top: 11px;
    padding-bottom: 10px;
    font-size: 16px;
    font-weight: 700;
    color: #fff;
    width: calc(250px);
    text-align: center;
}
</style>
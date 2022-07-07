
<template>
<form id="vaccine-interest-form" @submit.prevent="confirm"> 
    <div class="form-group">
        <label for="options">Одаберите опцију:</label>
        <select v-model="interesovanje.licneInformacije.drzavljanstvo" id="options" name="notResident" class="form-control">
            <option value="Држављанин Републике Србије">Држављанин Републике Србије</option>
            <option value="Страни држављанин са боравком у РС">Страни држављанин са боравком у РС</option>
            <option value="Страни држављанин без боравка у РС">Страни држављанин без боравка у РС</option>
        </select>
    </div>
    <div class="form-group">
        <label for="jmbg">ЈМБГ</label>
        <input v-model="interesovanje.licneInformacije.jmbg" type="text" id="jmbg" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="ime">Име</label>
        <input v-model="interesovanje.licneInformacije.ime" type="text" id="ime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="prezime">Презиме</label>
        <input v-model="interesovanje.licneInformacije.prezime" type="text" id="prezime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.lastName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="email">Адреса електронске поште</label>
        <input v-model="interesovanje.licneInformacije.kontakt.email._text" type="text" id="email" class="form-control" required>
        <div class="invalid-feedback">Невалидан формат</div>
    </div>
    <div class="form-group">
        <label for="mobilni">Број мобилног телефона (навести број у формату 06X..... без размака и цртица)</label>
        <input v-model="interesovanje.licneInformacije.kontakt.mobilniTelefon._text" type="text" id="mobilni" class="form-control" required>
        <div class="invalid-feedback">Невалидан формат</div>
    </div>
    <div class="form-group">
        <label for="fiksni">Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица):</label>
        <input v-model="interesovanje.licneInformacije.kontakt.fiksniTelefon._text" type="text" id="fiksni" class="form-control" required>
        <div class="invalid-feedback">Невалидан формат</div>
    </div>
    <div class="form-group">
        <label for="lokacija">Одаберите локацију где желите да примите вакцину</label>
        <input v-model="interesovanje.lokacijaPrimanja" type="text" id="lokacija" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.municipalityOptionId" style="display: none;"></span>
    </div>

    <div class="form-group">
        <label></label>
        <div class="checkbox-from-control">
            <input type="radio" name="agreed" v-model="saglasnost" value="da"><span>Исказујем интересовање да примим искључиво вакцину одређених произвођача за који Агенција за лекове и медицинска средства потврди безбедност, ефикасност и квалитет и изда дозволу за употребу лека.</span>
        </div>
        <span class="validation-error" data-bind="checked: patientInformation.agreed" title=""></span>
    </div>

    <div v-if="saglasnost" class="form-group vaccine-types">
        <label>
            <strong>Тип вакцине:</strong>
        </label>
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Pfizer-BioNTech" id="Pfizer" @click="onlyOneCheckbox('Pfizer-BioNTech')">
            <label for="Pfizer">Pfizer-BioNTech</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Sputnik V (Gamaleya истраживачки центар)" id="Sputnik" @click="onlyOneCheckbox('Sputnik V (Gamaleya истраживачки центар)')">
            <label for="Sputnik">Sputnik V (Gamaleya истраживачки центар)</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Sinopharm" id="Sinopharm" @click="onlyOneCheckbox('Sinopharm')">
            <label for="Sinopharm">Sinopharm</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="AstraZeneca" id="AstraZeneca" @click="onlyOneCheckbox('AstraZeneca')">
            <label for="AstraZeneca">AstraZeneca</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Moderna" id="Moderna" @click="onlyOneCheckbox('Moderna')">
            <label for="Moderna">Moderna</label>
        </div>
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Bilo koja" id="Bilo koja" @click="onlyOneCheckbox('Било која')">
            <label for="Bilo koja">Било која</label>
        </div>
    </div>

    <div class="form-group">
        <label for="dobrovoljniDavalacKrvi">Да ли сте добровољни давалац крви?</label>
        <div class="checkbox-from-control">
            <input v-model="interesovanje.davalacKrvi" type="radio" id="dobrovoljniDavalacKrvi" value="Не"><span>Не</span>
        </div>
        <div class="checkbox-from-control">
            <input v-model="interesovanje.davalacKrvi" type="radio" id="dobrovoljniDavalacKrvi" value="Да"><span>Да</span>
        </div>
    </div>

    <div class="bottom">
        <button class="button secondary" type="submit">Поднеси захтев</button>
    </div>
</form>
</template>

<script>
import xmljs from "xml-js";
import { mapState } from "vuex";
import InteresovanjeService from "@/service/InteresovanjeService.js";


export default {
    name: "VaccineInterestForm",

    computed: {
        ...mapState([
            'user',
            'jmbgRegex',
            'emailRegex',
            'brojFiksnogRegex',
            'brojMobilnogRegex'
        ])
    },

    data() {
        return {
            interesovanje: {
                licneInformacije: {
                    drzavljanstvo: "",
                    jmbg: "",
                    ime: "",
                    prezime: "",
                    kontakt: {
                        fiksniTelefon: {
                            "_attributes": {
                                "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                            },
                            "_text": ""
                        },
                        mobilniTelefon: {
                            "_attributes": {
                                "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                            },
                            "_text": ""
                        },
                        email: {
                            "_attributes": {
                                "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                            },
                            "_text": ""
                        }
                    }
                },
                lokacijaPrimanja: "",
                nazivVakcine: "",
                davalacKrvi: "",
                datum: ""
            },

            saglasnost: "",            
        };
    },

    created() {
        this.interesovanje.licneInformacije.ime = this.user.ime;
        this.interesovanje.licneInformacije.prezime = this.user.prezime;
        this.interesovanje.licneInformacije.kontakt.email._text = this.user.email;
        this.interesovanje.licneInformacije.drzavljanstvo = "Држављанин Републике Србије";
    },

    methods: {

        confirm() {
            this.resetInvalidStates();

            if (!this.validateForm())
                return;

            this.interesovanje.datum = new Date().toISOString().slice(0, 10);

            let data = "<interesovanje xmlns='http://www.ftn.uns.ac.rs/interesovanje'>" + xmljs.json2xml(this.interesovanje, {compact: true, spaces: 4}) + "</interesovanje>";
            InteresovanjeService.createInteresovanje(data)
                .then(() => { this.toast("Успешно сте поднели захтев за вакцинисање против COVID-19", "success"); })
                .catch(error => { this.toast("Невалидан унос података! Покушајте поново.", "error"); console.log(error.response.data) });
        },

        resetInvalidStates() {
            let elements = document.querySelectorAll(".form-group input");
            elements.forEach(el => el.classList.remove("is-invalid"));

            let form = document.getElementById("vaccine-interest-form");
            form.classList.remove("was_validated");
        },

        validateForm() {
            let form = document.getElementById("vaccine-interest-form");
            form.classList.add("was_validated");

            if (!form.checkValidity())
                return false;

            if (!this.jmbgRegex.test(this.interesovanje.licneInformacije.jmbg)) {
                this.setInputInvalid(form, "jmbg");
                return false;
            }

            // eslint-disable-next-line
            if (!this.emailRegex.test(this.interesovanje.licneInformacije.kontakt.email._text)) {
                this.setInputInvalid(form, "email");
                return false;
            }

            if (!this.brojFiksnogRegex.test(this.interesovanje.licneInformacije.kontakt.fiksniTelefon._text)) {
                this.setInputInvalid(form, "fiksni");
                return false;       
            }

            if (!this.brojMobilnogRegex.test(this.interesovanje.licneInformacije.kontakt.mobilniTelefon._text)) {
                this.setInputInvalid(form, "mobilni");
                return false;
            }

            return true;
        },

        setInputInvalid(form, inputId) {
            let inputEl = document.getElementById(inputId);
            inputEl.classList.add("is-invalid");
            form.checkValidity();
        },

        onlyOneCheckbox(checkbox) {
            this.interesovanje.nazivVakcine = checkbox;
            let checkboxes = document.getElementsByName('vaccinetypes')
            checkboxes.forEach((item) => {
                if (item.value !== checkbox) item.checked = false
            });
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

.vaccine-types {
    margin-left: 2rem;
}

.bottom {
    margin-top: 2rem;
    text-align: right;
    display: flex;
    justify-content: flex-end;}

.button.secondary {
    transition: .3s !important;
    background-color: #017932 !important;
    border: none !important;
    font-family: inherit;
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
    margin-left: 20px;
}

.button.tertiary {
    background-color: #74222a !important;
    border: none !important;
}
</style>
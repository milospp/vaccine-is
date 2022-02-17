
<template>
<form id="vaccine-interest-form" @submit.prevent="confirm">
    <div class="form-group">
        <label for="ime">Име</label>
        <input v-model="zahtev.ime" type="text" id="ime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="prezime">Презиме</label>
        <input v-model="zahtev.prezime" type="text" id="prezime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.lastName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="datumRodjenja">Датум рођења</label>
        <input v-model="zahtev.datumRodjenja" type="date" id="datumRodjenja" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>    
    <div class="form-group">
        <label for="jmbg">ЈМБГ</label>
        <input v-model="zahtev.jmbg" type="text" id="jmbg" class="form-control" required> 
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
    <div class="form">
        <label>Пол?</label>
        <div class="checkbox-from-control">
            <input v-model="zahtev.pol" type="radio" name="specificVaccinationDateChecked" value="Мушко" class="" title=""><span>Мушко</span>
        </div>
        <div class="checkbox-from-control">
            <input v-model="zahtev.pol" type="radio" name="specificVaccinationDateChecked" value="Женско" class="" title=""><span>Женско</span>
        </div>
        <span class="validation-error" data-bind="checked: patientInformation.specificVaccinationDateChecked" title=""></span>
    </div>
    <br>
    <div class="form-group">
        <label for="pasos">Број пасоша</label>
        <input v-model="zahtev.brojPasosa" type="text" id="mobilni" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.contactPhone" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="razlog">Разлог за подношење захтева</label>
        <input v-model="zahtev.razlog" type="text" id="razlog" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.email" style="display: none;"></span>
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

import xmljs from "xml-js";


export default {
    name: "DigitalGreenCertificateForm",

    data() {
        return {
            zahtev: {
                ime: "",
                prezime: "",
                jmbg: "",
                pol: "",
                datumRodjenja: "",
                brojPasosa: "",
                razlog: "",
                mjestoDatum: {
                    lokacija: "",
                    datum: ""
                }
            },
        };
    },

    methods: {
        confirm() {
            this.zahtev.mjestoDatum.datum = new Date();

            let data = "<zahtjev>" + xmljs.json2xml(this.zahtev, {compact: true, spaces: 4}) + "</zahtjev>";
            DigitalniZeleniSertifikatService.createZahtev(data)
                .then(() => { this.toast("Успешно сте поднели захтев за дигитални зелени сертификат", "success"); })
                .catch(error => console.log(error));
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
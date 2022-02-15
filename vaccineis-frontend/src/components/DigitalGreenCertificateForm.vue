
<template>
<form id="vaccine-interest-form">
    <div class="form-group">
        <label for="ime">Име (*)</label>
        <input v-model="patientInfo.ime" type="text" id="ime" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="prezime">Презиме (*)</label>
        <input v-model="patientInfo.prezime" type="text" id="prezime" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.lastName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="datumRodjenja">Датум рођења (*)</label>
        <input v-model="patientInfo.birthdate" type="text" id="datumRodjenja" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>    
    <div class="form-group">
        <label for="jmbg">ЈМБГ (*)</label>
        <input v-model="patientInfo.jmbg" type="text" id="jmbg" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="email">Адреса електронске поште (*)</label>
        <input v-model="patientInfo.email" type="text" id="email" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.email" style="display: none;"></span>
    </div>
    <div class="form">
        <label>Пол?</label>
        <div class="checkbox-from-control">
            <input v-model="patientInfo.pol" type="radio" name="specificVaccinationDateChecked" value="musko" class="" title=""><span>Мушко</span>
        </div>
        <div class="checkbox-from-control">
            <input v-model="patientInfo.zensko" type="radio" name="specificVaccinationDateChecked" value="zensko" class="" title=""><span>Женско</span>
        </div>
        <span class="validation-error" data-bind="checked: patientInformation.specificVaccinationDateChecked" title=""></span>
    </div>
    <br>
    <div class="form-group">
        <label for="pasos">Број пасоша (*)</label>
        <input v-model="patientInfo.telefon" type="text" id="mobilni" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.contactPhone" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="razlog">Разлог за подношење захтева (*)</label>
        <input v-model="patientInfo.razlog" type="text" id="razlog" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.email" style="display: none;"></span>
    </div>
    <br>
    <div class="bottom">
        <button class="button secondary" href="#" data-bind="click: save">Поднеси захтев</button>
    </div>
</form>
</template>

<script>
export default {
    name: "DigitalGreenCertificateForm",

    data() {
        return {
        patientInfo: {
            jmbg: "",
            password: "",
            ime: "",
            prezime: "",
        },
        };
    },

    methods: {

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
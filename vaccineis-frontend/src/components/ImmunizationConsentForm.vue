<template>
<form id="vaccine-interest-form">
    <div class="form-group">
        <label for="options">Држављанство (*)</label>
        <select v-model="citizenship" id="options" name="notResident" class="form-control">
            <option value="1">Држављанин Републике Србије</option>
            <option value="2">Страни држављанин</option>
        </select>
    </div>
    <div v-if="citizenship == '1'" class="form-group">
        <label for="jmbg">ЈМБГ (*)</label>
        <input v-model="patientInfo.jmbg" type="text" id="jmbg" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
    <div v-if="citizenship == '2'" class="form-group">
        <label for="jmbg">Назив страног држављанства (*)</label>
        <input v-model="patientInfo.jmbg" type="text" id="jmbg" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
    <div v-if="citizenship == '2'" class="form-group">
        <label for="jmbg">Бр. пасоша или ЕБС за стране држављане (*)</label>
        <input v-model="patientInfo.jmbg" type="text" id="jmbg" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="prezime">Презиме (*)</label>
        <input v-model="patientInfo.prezime" type="text" id="prezime" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.lastName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="ime">Име (*)</label>
        <input v-model="patientInfo.ime" type="text" id="ime" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="ime">Име родитеља (*)</label>
        <input v-model="patientInfo.ime" type="text" id="ime" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="pol">Пол (*)</label>
        <div>
            <input type="radio" id="musko" name="pol" value="musko">
            <label for="musko">Мушко</label>
        </div>
        <div>
            <input type="radio" id="zensko" name="pol" value="zensko">
            <label for="zensko">Женско</label>
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfBirth">Датум рођења (*)</label>
        <input type="date" id="dateOfBirth" class="form-control">
    </div>
    <div class="form-group">
        <label for="placeOfBirth">Место рођења (*)</label>
        <input type="text" id="placeOfBirth" class="form-control">
    </div>
    <div class="form-group">
        <label for="address">Адреса (улица и број) (*)</label>
        <input type="text" id="address" class="form-control">
    </div>
    <div class="form-group">
        <label for="place">Место/Насеље (*)</label>
        <input type="text" id="place" class="form-control">
    </div>
    <div class="form-group">
        <label for="municipality">Општина/Град (*)</label>
        <input type="text" id="municipality" class="form-control">
    </div>
    <div class="form-group">
        <label for="homePhoneNumber">Тел. фиксни (*)</label>
        <input type="text" id="homePhoneNumber" class="form-control">
    </div>
    <div class="form-group">
        <label for="mobilePhoneNumber">Тел. мобилни (*)</label>
        <input type="text" id="mobilePhoneNumber" class="form-control">
    </div>
    <div class="form-group">
        <label for="email">Имејл (*)</label>
        <input type="text" id="email" class="form-control">
    </div>
    <div class="form-group">
        <label for="status">Радни статус (*)</label>
        <div>
            <input type="radio" id="zaposlen" name="status" v-model="status" value="zaposlen">
            <label for="status">Запослен</label>
        </div>
        <div>
            <input type="radio" id="nezaposlen" name="status" v-model="status" value="nezaposlen">
            <label for="status">Незапослен</label>
        </div>
        <div>
            <input type="radio" id="penzioner" name="status" v-model="status" value="penzioner">
            <label for="status">Пензионер</label>
        </div>
        <div>
            <input type="radio" id="ucenik" name="status" v-model="status" value="ucenik">
            <label for="status">Ученик</label>
        </div>
        <div>
            <input type="radio" id="student" name="status" v-model="status" value="student">
            <label for="status">Студент</label>
        </div>
        <div>
            <input type="radio" id="dete" name="status" v-model="status" value="dete">
            <label for="status">Дете</label>
        </div>
    </div>
    <div v-if="status == 'zaposlen'" class="form-group">
        <label for="occupation">Занимање запосленог (*)</label>
        <div>
            <input type="radio" id="zdravstvenaZastita" name="occupation" v-model="occupation" value="zdravstvenaZastita">
            <label for="occupation">Здравствена заштита</label>
        </div>
        <div>
            <input type="radio" id="socijalnaZastita" name="occupation" v-model="occupation" value="socijalnaZastita">
            <label for="occupation">Социјална заштита</label>
        </div>
        <div>
            <input type="radio" id="prosveta" name="occupation" v-model="occupation" value="prosveta">
            <label for="occupation">Просвета</label>
        </div>
        <div>
            <input type="radio" id="mup" name="occupation" v-model="occupation" value="mup">
            <label for="occupation">МУП</label>
        </div>
        <div>
            <input type="radio" id="vojskaRS" name="occupation" v-model="occupation" value="vojskaRS">
            <label for="occupation">Војска РС</label>
        </div>
        <div>
            <input type="radio" id="drugo" name="occupation" v-model="occupation" value="drugo">
            <label for="occupation">Друго</label>
        </div>
    </div>
    <div class="form-group">
        <label for="socialSecurityBeneficiary">Корисник установе соц. зашт. (*)</label>
        <div>
            <input type="radio" id="da" name="socialSecurityBeneficiary" v-model="municipalHeadquarters" value="1">
            <label for="socialSecurityBeneficiary">Да</label>
        </div>
        <div>
            <input type="radio" id="ne" name="socialSecurityBeneficiary" v-model="municipalHeadquarters" value="2">
            <label for="socialSecurityBeneficiary">Не</label>
        </div>
    </div>
    <div v-if="municipalHeadquarters == '1'" class="form-group">
        <label for="municipalHeadquarters">Назив и општина седишта (*)</label>
        <input type="text" id="municipalHeadquarters" class="form-control">
    </div>

    <div class="form-group">
        <label for="agreement">Изјављујем да: (*)</label>
        <div>
            <input type="radio" id="saglasan" name="agreement" v-model="agreement" value="1">
            <label for="agreement">САГЛАСАН САМ</label>
        </div>
        <div>
            <input type="radio" id="nesaglasan" name="agreement" v-model="agreement" value="2">
            <label for="agreement">НИСАМ САГЛАСАН</label>
        </div>
        <label for="drug">са спровођењем активне/пасивне имунизације (уписати назив имунолошког лека): </label>
        <input type="text" name="drug" id="drug" class="form-control">
    </div>

    <div class="form-group">
        <label style="color: #74222a;">Обавезна поља су означена звездицом (*)</label>
    </div>

    <div class="bottom">
        <button class="button tertiary" href="#" v-on:click="decline()">Одустани</button>
        <button class="button secondary" href="#" v-on:click="accept()">Поднеси захтев</button>
    </div>
</form>
</template>

<script>
export default {
    name: "VaccineInterestForm",

    data() {
        return {
        patientInfo: {
            jmbg: "",
            password: "",
            ime: "",
            prezime: "",
        },
        citizenship: "",
        status: null,
        socialSecurityBeneficiary: null,
        municipalHeadquarters: null,
        };
    },

    methods: {

    },
};
</script>


<style scoped>
.bottom {
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
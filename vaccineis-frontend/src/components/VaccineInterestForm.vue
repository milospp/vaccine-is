
<template>
<form id="vaccine-interest-form">
    <div class="form-group">
        <label for="options">Одаберите опцију:</label>
        <select id="options" name="notResident" class="form-control">
            <option value="1">Држављанин Републике Србије</option>
            <option value="2">Страни држављанин са боравком у РС</option>
            <option value="3">Страни држављанин без боравка у РС</option>
        </select>
    </div>
    <div class="form-group">
        <label for="jmbg">ЈМБГ (*)</label>
        <input v-model="patientInfo.jmbg" type="text" id="jmbg" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
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
        <label for="email">Адреса електронске поште (*)</label>
        <input v-model="patientInfo.email" type="text" id="email" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.email" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="mobilni">Број мобилног телефона (навести број у формату 06X..... без размака и цртица) (*)</label>
        <input v-model="patientInfo.telefon" type="text" id="mobilni" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.contactPhone" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="fiksni">Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица):</label>
        <input v-model="patientInfo.fiksni" type="text" id="fiksni" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.homePhone" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="lokacija">Одаберите локацију где желите да примите вакцину (*)</label>
        <input v-model="patientInfo.lokacija" type="text" id="lokacija" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.municipalityOptionId" style="display: none;"></span>
    </div>

    <div class="form-group">
        <label></label>
        <div class="checkbox-from-control">
            <input v-model="patientInfo.tip" type="radio" name="agreed" value="no"><span>Исказујем интересовање да примим било који тип вакцине за који Агенција за лекове и медицинска средства потврди безбедност, ефикасност и квалитет и изда дозволу за употребу лека.</span>
        </div>
        <div class="checkbox-from-control">
            <input v-model="patientInfo.tip" type="radio" name="agreed" value="yes"><span>Исказујем интересовање да примим искључиво вакцину одређених произвођача за који Агенција за лекове и медицинска средства потврди безбедност, ефикасност и квалитет и изда дозволу за употребу лека.</span>
        </div>
        <span class="validation-error" data-bind="checked: patientInformation.agreed" title=""></span>
    </div>

    <div v-if="patientInfo.tip === 'yes'" class="form-group">
        <label>
            <strong>Тип вакцине:</strong>
        </label>
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Pfizer" id="Pfizer">
            <label data-bind="text: name, attr: { for: id }" for="Pfizer">Pfizer-BioNTech</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Sputnik" id="Sputnik">
            <label data-bind="text: name, attr: { for: id }" for="Sputnik">Sputnik V (Gamaleya истраживачки центар)</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Sinopharm" id="Sinopharm">
            <label data-bind="text: name, attr: { for: id }" for="Sinopharm">Sinopharm</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="AstraZeneca" id="AstraZeneca">
            <label data-bind="text: name, attr: { for: id }" for="AstraZeneca">AstraZeneca</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="vaccinetypes" value="Moderna" id="Moderna">
            <label data-bind="text: name, attr: { for: id }" for="Moderna">Moderna</label>
        </div>
    </div>

    <div class="form-group">
        <label for="specificna-oboljenja">Да ли имате неко од специфичних обољења?</label>
        <div class="checkbox-from-control">
            <input v-model="patientInfo.specificnaOboljenja" type="radio" id="specificnaOboljenja" value="no"><span>Не</span>
        </div>
        <div class="checkbox-from-control">
            <input v-model="patientInfo.specificnaOboljenja" type="radio" id="specificnaOboljenja" value="yes"><span>Да</span>
        </div>
        <span class="validation-error" data-bind="checked: patientInformation.specificDiseasesChecked" title=""></span>
    </div>

    <div v-if="patientInfo.specificnaOboljenja === 'yes'" class="form-group">
        <label><strong>Означите уколико имате једно или више од наведених специфичних обољења:</strong></label>

        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="1" id="1">
            <label data-bind="text: name, attr: { for: id }" for="1">Малигна обољења (пацијенти на хемиотерапији, малигне хемопатије, недавна трансплатација матичних ћелија)</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="2" id="2">
            <label data-bind="text: name, attr: { for: id }" for="2">Хронична обољења срца и крвних судова (тешка декомпензација срца: попуштање срца, кардиомиопатија)</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="3" id="3">
            <label data-bind="text: name, attr: { for: id }" for="3">Хронична бубрежна обољења и дијализа</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="4" id="4">
            <label data-bind="text: name, attr: { for: id }" for="4">Хронична обољења јетре (осим хроничних инфекција хепатитис Б или хепатитис Ц вирусом)</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="5" id="5">
            <label data-bind="text: name, attr: { for: id }" for="5">Хронична обољења плућа (хронична опструктивна болест плућа, цистична фиброза, интерстицијске болести плућа, примарна плућна хипертензија, пацијенти који захтевају ДОТ или НИВ у кућним условима)</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="6" id="6">
            <label data-bind="text: name, attr: { for: id }" for="6">Дијабетес (особе на терапији инсулином уколико је потребно пооштравати критеријум, односно све особе са дијагностикованим дијабетесом уколико имамо на располагању довољне количине вакцине)</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="7" id="7">
            <label data-bind="text: name, attr: { for: id }" for="7">Стања имуносупресије, укључујући и трансплантацију органа</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="8" id="8">
            <label data-bind="text: name, attr: { for: id }" for="8">Неуролошка обољења, укључујући и цереброваскуларна обољења</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="9" id="9">
            <label data-bind="text: name, attr: { for: id }" for="9">Гојазност (BMI&gt; 40 кг/м2)</label>
        </div>
        
        <div class="checkbox" style="width: 100%;">
            <input type="checkbox" name="specificdiseases" data-bind="attr: { value: id, id: id }, jqmChecked: selected" value="10" id="10">
            <label data-bind="text: name, attr: { for: id }" for="10">Резистентна хипертензија са вредностима крвног притиска већим од 140/90 mmHg упркос трострукој комбинацији хипертензива</label>
        </div>
    </div>

    <div class="form-group">
        <label>Да ли због здравствених проблема не можете да излазите из куће/стана?</label>
        <div class="checkbox-from-control">
            <input type="radio" name="onlyInHouse" value="no" data-bind="checked: patientInformation.onlyInHouse" class="" title=""><span>Не, немам таквих здравствених проблема</span>
        </div>
        <div class="checkbox-from-control">
            <input type="radio" name="onlyInHouse" value="yes" data-bind="checked: patientInformation.onlyInHouse" class="" title=""><span>Да, не могу да излазим из куће/стана</span>
        </div>
        <span class="validation-error" data-bind="checked: patientInformation.agreed" title=""></span>
    </div>

    <div class="form-group">
        <label for="dobrovoljniDavalacKrvi">Да ли сте добровољни давалац крви?</label>
        <div class="checkbox-from-control">
            <input v-model="patientInfo.dobrovoljniDavalacKrvi" type="radio" id="dobrovoljniDavalacKrvi" value="no"><span>Не</span>
        </div>
        <div class="checkbox-from-control">
            <input v-model="patientInfo.dobrovoljniDavalacKrvi" type="radio" id="dobrovoljniDavalacKrvi" value="yes"><span>Да</span>
        </div>
        <label style="color: #74222a;"> * Приликом доласка у заказан термин вакцинације потребно је да донесете доказ да сте добровољни давалац крви.</label>
    </div>


    <div class="form">
        <label>Да ли можете доћи у било којем термину који закаже еУправа?</label>
        <div class="checkbox-from-control">
            <input type="radio" name="specificVaccinationDateChecked" value="no" data-bind="checked: patientInformation.specificVaccinationDateChecked" class="" title=""><span>Могу доћи у било ком термину који закаже еУправа</span>
        </div>
        <div class="checkbox-from-control">
            <input type="radio" name="specificVaccinationDateChecked" value="yes" data-bind="checked: patientInformation.specificVaccinationDateChecked" class="" title=""><span>Могу доћи само у термину који је после назначеног датума</span>
        </div>
        <span class="validation-error" data-bind="checked: patientInformation.specificVaccinationDateChecked" title=""></span>
    </div>

    <div data-bind="visible: patientInformation.specificVaccinationDateChecked() == 'yes'" class="form" style="display: none;">
        <label>
            <strong>Одаберите датум након којег можете доћи на вакцинацију (упишите датум у формату DD.MM.GGGG.): </strong>
        </label>
        <input type="text" id="specificVaccinationDate" name="specificVaccinationDate" data-bind="datepicker: patientInformation.specificVaccinationDate" required="" class="hasDatepicker"><img class="ui-datepicker-trigger" src="/content/img/calendar.png" alt="..." title="...">
    </div>

    <br>
    
    <div class="form-group">
        <div class="checkbox-from-control">
            <input type="checkbox" name="agreedForDataUsage" data-bind="checked: patientInformation.agreedForDataUsage"><span><strong>Сагласан сам да овлашћено лице Института за јавно здравље Србије "Др Милан Јовановић Батут" обради наведене податке у вези са вакцинисањем против COVID-19 и предложи термин и место вакцинације.</strong></span>
        </div>
        <span class="validation-error" data-bind="checked: patientInformation.agreedForDataUsage" title=""></span>
    </div>

    <div class="form-group">
        <label style="color: #74222a;">Обавезна поља су означена звездицом (*)</label>
    </div>

    <div class="bottom">
        <button class="button tertiary">Одустани</button>
        <button class="button secondary" href="#" data-bind="click: save">Поднеси захтев</button>
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
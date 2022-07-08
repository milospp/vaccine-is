<template>
<form @submit.prevent="confirm" id="immunization-consesnt-form" v-if="saglasnost['ns2:podaciPacijenta']">

    <div :style="[this.user.rola !== 'GRADJANIN' ? {'pointer-events': 'none'} : {}]" id="vaccine-interest-form">

    <div id="title">
        <h5><b>Сагласност за спровођење препоручене имунизације</b></h5>
        <svg width="200" height="200" xmlns="http://www.w3.org/2000/svg">
            <image href="../assets/images/batut.png" width="200" height="200" />
        </svg>
    </div>

    <div class="form-group">
        <label for="prezime">Презиме</label>
        <input v-model="saglasnost['ns2:podaciPacijenta'].prezime._text" type="text" id="prezime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.lastName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="ime">Име</label>
        <input v-model="saglasnost['ns2:podaciPacijenta'].ime._text" type="text" id="ime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="ime">Име родитеља</label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:imeRoditelja']._text" type="text" id="ime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="pol">Пол</label>
        <div>
            <input v-model="saglasnost['ns2:podaciPacijenta'].pol._text" type="radio" id="musko" name="pol" value="Мушко">
            <label for="musko">Мушко</label>
        </div>
        <div>
            <input v-model="saglasnost['ns2:podaciPacijenta'].pol._text" type="radio" id="zensko" name="pol" value="Женско">
            <label for="zensko">Женско</label>
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfBirth">Датум рођења</label>
        <!-- <input v-model="saglasnost.podaciPacijenta.datumRodjenja._text" type="date" id="datumRodjenja" class="form-control"  :max="date16YrsAgo" required>  -->
    </div>
    <div class="form-group">
        <label for="placeOfBirth">Место рођења</label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:podaciMjesta']['ns2:mjestoRodjenja']._text" type="text" id="placeOfBirth" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="address">Адреса (улица) </label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:podaciMjesta']['ns2:adresa'].ulica._text" type="text" id="address" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="broj">Адреса (број) </label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:podaciMjesta']['ns2:adresa'].broj._text" type="text" id="broj" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="place">Место/Насеље</label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:podaciMjesta']['ns2:mjestoNaselje']._text" type="text" id="mjestoNaselje" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="municipality">Општина/Град</label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:podaciMjesta']['ns2:opstinaGrad']._text" type="text" id="opstinaGrad" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="homePhoneNumber">Тел. фиксни</label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:kontaktPodaci'].fiksniTelefon._text" type="text" id="fiksni" class="form-control">
    </div>
    <div class="form-group">
        <label for="mobilePhoneNumber">Тел. мобилни</label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:kontaktPodaci'].mobilniTelefon._text" type="text" id="mobilni" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="email">Имејл</label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:kontaktPodaci'].email._text" type="text" id="email" class="form-control" required>
         <div class="invalid-feedback">Невалидан имејл.</div>
    </div>
    <div class="form-group">
        <label for="status">Радни статус</label>
        <div>
            <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:radniStatus']._text" name="radniStatus" id="zaposlen">
        </div>

    </div>
    <div v-if="saglasnost['ns2:podaciPacijenta']['ns2:radniStatus']._text === 'запослен'" class="form-group">
        <label for="occupation">Занимање запосленог </label>
        <div>
            <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:zanimanjeZaposlenog']._text" id="zdravstvenaZastita" name="zanimanjeZaposlenog" value="здравствена заштита">
        </div>
    </div>
    <div v-if="saglasnost['ns2:podaciPacijenta']['ns2:socijalnaZastita']['ns2:korisnikSocijalnog']" class="form-group">
        <label for="socialSecurityBeneficiary">Корисник установе соц. зашт. </label>
        <div>
            <input name="socialSecurityBeneficiary" v-model="saglasnost['ns2:podaciPacijenta']['ns2:socijalnaZastita']['ns2:korisnikSocijalnog']._text">
        </div>
    </div>

    <div v-if="saglasnost['ns2:podaciPacijenta']['ns2:socijalnaZastita']['ns2:korisnikSocijalnog'] === 'ДА'" class="form-group">
        <div class="form-group">
            <label for="nazivUstanove">Назив установе</label>
            <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:socijalnaZastita']['ns2:nazivUstanove']._text" type="text" id="nazivUstanove" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="sjedisteUstanove">Општина седишта</label>
            <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:socijalnaZastita']['ns2:sjedisteUstanove']._text" type="text" id="sjedisteUstanove" class="form-control" required>
        </div>
    </div>
    <div class="form-group">
        <label for="imeLijeka">Одабрана вакцина</label>
        <input v-model="saglasnost['ns2:podaciPacijenta']['ns2:izjavaSaglasnosti']['ns2:imeLijeka']._text" type="text" id="sjedisteUstanove" class="form-control" required>
    </div>

    <br>

    </div>

    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    <div id="vaccine-interest-form">
    <br><br>

    <div id="title2">
        <h5><b>Евиденција о вакцинацији против COVID-19</b></h5>
        <h6>(Попуњава здравствени радник)</h6>
    </div>

    <br><br>

    <div class="form-group">
        <label for="healthInstitution">Здравствена установа</label>
        <input v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:zdravstvenaUstanova']['ns2:nazivZdravstveneUstanove']" type="text" id="healthInstitution" class="form-control">
    </div>

    <div class="form-group">
        <label for="healthInstitution">Вакцинацијски пункт</label>
        <input v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:zdravstvenaUstanova']['ns2:vakcinacijskiPunkt']" type="text" id="healthInstitution" class="form-control">
    </div>


    <div class="form-group">
        <label for="doctorInfo">Име доктора</label>
        <input v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciLjekara']['ns2:ime']" type="text" id="doctorInfo" class="form-control">
    </div>


    <div class="form-group">
        <label for="doctorInfo">Презиме доктора</label>
        <input v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciLjekara']['ns2:prezime']" type="text" id="doctorInfo" class="form-control">
    </div>


    <div class="form-group">
        <label for="doctorInfo">Факсимил доктора</label>
        <input v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciLjekara']['ns2:faksimil']" type="text" id="doctorInfo" class="form-control">
    </div>


    <div class="form-group">
        <label for="doctorInfo">Број телефона доктора</label>
        <input v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciLjekara']['ns2:brojTelefona']" type="text" id="doctorInfo" class="form-control">
    </div>

    <p>
        Пре давања вакцине прегледати особу и упознати је са користима и о могућим нежељеним реакцијама после 
        вакцинације. Обавезно уписати сваку дату вакцину и све тражене податке у овај образац и податке унети у лични 
        картон о извршеним имунизацијама и здравствени картон. 
    </p>

    <table border="1">
        <thead>
            <th>Назив вакцине</th>
            <th>Датум давања вакцине (V1 i V2)</th>
            <th>Начин давања вакцине</th>
            <th>Екстремитет</th>
            <th>Серија вакцине (лот)</th>
            <th>Произвођач</th>
            <th>Нежељена реакција</th>
        </thead>
        <tbody>
            <tr :style="[{'pointer-events': 'none'}]"  v-for="vakcina in saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija']['ns2:vakcinacija']" :key="vakcina.id">
                <td><input v-model="vakcina['ns2:nazivVakcine']" type="text" id="vaccineName" class="form-control"></td>
                <td><input v-model="vakcina['ns2:datumDavanjaVakcine']" type="date" id="vaccineDate" class="form-control"></td>
                <td><input v-model="vakcina['ns2:nacinDavanjaVakcine']"></td>
                <td><input v-model="vakcina['ns2:ekstremitet']"></td>
                <td>
                    <input v-model="vakcina['ns2:serijaVakcine']" type="text" id="vaccineLot" class="form-control">
                </td>
                <td>
                    <input v-model="vakcina['ns2:proizvodjac']" type="text" id="vaccineProducer" class="form-control">
                </td>
                <td>
                    <input v-model="vakcina['ns2:nezeljenaReakcija']" type="text" id="vaccineReaction" class="form-control">
                </td>
            </tr>
            <tr>
                <td>
                    <select id="options" v-model="novaVakcina['ns2:nazivVakcine']" name="notResident" class="form-control" required>
                        <option value="Pfizer-BioNTech">Pfizer-BioNTech</option>
                        <option value="Sputnik V (Gamaleya истраживачки центар)">Sputnik V</option>
                        <option value="Sinopharm">Sinopharm</option>
                        <option value="AstraZeneca">AstraZeneca</option>
                        <option value="Moderna">Moderna</option>
                    </select>           
                </td>
                <td><input v-model="novaVakcina['ns2:datumDavanjaVakcine']" type="date" id="vaccineDate" class="form-control"></td>
                <td><input v-model="novaVakcina['ns2:nacinDavanjaVakcine']"></td>
                <td>
                    <select id="options" v-model="novaVakcina['ns2:ekstremitet']" name="notResident" class="form-control" required>
                        <option value="Десна рука">Десна рука</option>
                        <option value="Лијева рука">Лева рука</option>
                    </select>
                </td>
                <td>
                    <input v-model="novaVakcina['ns2:serijaVakcine']" type="text" id="vaccineLot" class="form-control">
                </td>
                <td>
                    <input v-model="novaVakcina['ns2:proizvodjac']" type="text" id="vaccineProducer" class="form-control">
                </td>
                <td>
                    <input v-model="novaVakcina['ns2:nezeljenaReakcija']" type="text" id="vaccineReaction" class="form-control">
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    Привремене контраиндикације (датум утврђивања): 
                    <input v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija']['ns2:privremeneKontraindikacije']['ns2:kontraindikacija']['ns2:datumUtvrdjivanja']" type="date" id="vaccineTemporaryContraindication" class="form-control">
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    Привремене контраиндикације (дијагноза): 
                    <input v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija']['ns2:privremeneKontraindikacije']['ns2:kontraindikacija']['ns2:dijagnoza']" type="text" id="vaccineTemporaryContraindication" class="form-control">
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    Одлука комисије за трајне контраиндикације (ако постоји, уписати Да): 
                    <select id="options" v-model="saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija']['ns2:odlukaKomisije']" name="notResident" class="form-control" required>
                        <option value="Да">Да</option>
                        <option value="Не">Не</option>
                    </select>           
                </td>
            </tr>
        </tbody>
    </table>

    <div class="bottom">
        <button class="button secondary" type="submit">Поднеси захтев</button>
    </div>

    </div>

</form>

</template>

<script>
import ObrazacSaglasnostiService from "@/service/ObrazacSaglasnostiService.js";
import { mapState } from "vuex";
import xmljs from "xml-js";

export default {
    name: "ImmunizationConsenstFormPart2",

    computed: {
        ...mapState([
            'user',
            'brojFiksnogRegex',
            'brojMobilnogRegex',
            'emailRegex'
        ])
    },

    props: {
        id: String
    },

    data() {
        return {
            drzavljanstvoIzbor: "1",
            saglasnost: {},
            novaVakcina: {},

            saglasnostOrder: {
                'ns2:drzavljanstvo': {},
                'ns2:podaciPacijenta': {
                    ime: {},
                    prezime: {},
                    pol: {},
                    datumRodjenja: {},
                    'ns2:imeRoditelja': "",
                    'ns2:podaciMjesta': {
                        'ns2:mjestoRodjenja': "",
                        'ns2:adresa': {
                            ulica: {},
                            broj: {}
                        },
                        'ns2:mjestoNaselje': "",
                        'ns2:opstinaGrad': "",
                    },
                    'ns2:kontaktPodaci': {
                        fiksniTelefon: {},
                        mobilniTelefon: {},
                        email: {}
                    },
                    'ns2:radniStatus': "",
                    'ns2:zanimanjeZaposlenog': "",
                    'ns2:socijalnaZastita': {
                        'ns2:korisnikSocijalnog': "",
                        'ns2:nazivUstanove': "",
                        'ns2:sjedisteUstanove': "",
                        'ns2:nijeKorisnikSocijalnog': ""
                    },
                    'ns2:izjavaSaglasnosti': {
                        'ns2:saglasan': "",
                        'ns2:imeLijeka': "",
                        'ns2:nesaglasan': ""
                    },
                    'ns2:datumSaglasnosti': Date
                },
                
                'ns2:evidencijaVakcinacije': {
                  
                    'ns2:zdravstvenaUstanova': {
                        'ns2:nazivZdravstveneUstanove': "",
                        'ns2:vakcinacijskiPunkt': ""
                    },  

                    'ns2:podaciLjekara': {
                        'ns2:ime': "",
                        'ns2:prezime': "",
                        'ns2:faksimil': "",
                        'ns2:brojTelefona': ""
                    },

                    'ns2:podaciVakcinacija': {

                        'ns2:vakcinacija': [],

                        'ns2:privremeneKontraindikacije': {
                            'ns2:kontraindikacija': {
                                'ns2:datumUtvrdjivanja': "",
                                'ns2:dijagnoza': ""
                            }
                        },

                        'ns2:odlukaKomisije': ""
                    }
                },
            }
        }
    },

    created() {
        ObrazacSaglasnostiService.getObrazacById(this.id)
            .then(response => {
                let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4})); 
                this.saglasnost = data["ns2:saglasnost"];

                this.saglasnost['ns2:evidencijaVakcinacije'] = {};

                this.saglasnost['ns2:evidencijaVakcinacije']['ns2:zdravstvenaUstanova'] = {
                    'ns2:nazivZdravstveneUstanove': "",
                    'ns2:vakcinacijskiPunkt': ""
                };

                this.saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciLjekara'] = {
                    'ns2:ime': "",
                    'ns2:prezime': "",
                    'ns2:faksimil': "",
                    'ns2:brojTelefona': ""
                };
                
                this.saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija'] = {
                    'ns2:vakcinacija': []
                };

                this.saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija']['ns2:privremeneKontraindikacije'] = {}
                this.saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija']['ns2:privremeneKontraindikacije']['ns2:kontraindikacija'] = {
                    'ns2:datumUtvrdjivanja': "",
                    'ns2:dijagnoza': ""
                };

                this.saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija']['ns2:odlukaKomisije'] = "";
            })
            .catch(error => {
                console.log(error);
            })

        // this.date16YrsAgo.setFullYear(this.date16YrsAgo.getFullYear() - 16);
        // let d = this.date16YrsAgo;

        // this.date16YrsAgo =  d.getFullYear() +  "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2);
    },

    methods: {
        
        confirm() {
            this.resetInvalidStates();

            if (!this.validateForm())
                return;

            this.saglasnost['ns2:evidencijaVakcinacije']['ns2:podaciVakcinacija']['ns2:vakcinacija'].push(this.novaVakcina);
            let id = this.saglasnost.id._text;

            this.prepareSaglasnost();
            let data = "<ns2:saglasnost xmlns='http://www.ftn.uns.ac.rs/zajednicka' xmlns:ns2='http://www.ftn.uns.ac.rs/obrazac-saglasnosti'>" + xmljs.json2xml(this.saglasnost, {compact: true, spaces: 4}) + "</ns2:saglasnost>";
            console.log("podneo", data);

            console.log(id);
            ObrazacSaglasnostiService.odgovoriNaObrazac(id, data)
                .then(() => { this.toast("Успешно сте поднели захтев за вакцинисање против COVID-19", "success"); })
                .catch(error => { this.toast("Невалидан унос података! Покушајте поново.", "error"); console.log(error.response.data.message) });
        },

        resetInvalidStates() {
            let elements = document.querySelectorAll(".form-group input");
            elements.forEach(el => el.classList.remove("is-invalid"));

            let form = document.getElementById("vaccine-interest-form");
            form.classList.remove("was_validated");
        },

        validateForm() {
            let form = document.getElementById("immunization-consesnt-form");
            form.classList.add("was_validated");

            if (!form.checkValidity())
                return false;

            return true;
        },

        prepareSaglasnost() {
            delete this.saglasnost.id;
            this.saglasnost = Object.assign(this.saglasnostOrder, this.saglasnost);
        },

        setInputInvalid(form, inputId) {
            let inputEl = document.getElementById(inputId);
            inputEl.classList.add("is-invalid");
            form.checkValidity();
        },

        onlyOneCheckbox(checkbox, elementName) {
            this.saglasnost['ns2:podaciPacijenta'][elementName] = checkbox;
            let checkboxes = document.getElementsByName(elementName)
            checkboxes.forEach((item) => {
                if (item.value !== checkbox) item.checked = false
            });
        },

        rename_attr(o, old_name, new_name) {
            Object.defineProperty(o, new_name,
                Object.getOwnPropertyDescriptor(o, old_name));
            delete o[old_name];
            return o;
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

    filters: {
        date: function(date) {
            return date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear();
        }
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

#title {
    text-align: left;
    font-weight: 50;
    color: black;
}

#title2 {
    text-align: center;
    font-weight: 50;
    color: black;
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
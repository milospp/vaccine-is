<template>
<form @submit.prevent="confirm" id="immunization-consesnt-form">

    <div :style="[this.user.rola !== 'GRADJANIN' ? {'pointer-events': 'none'} : {}]" id="vaccine-interest-form">

    <div id="title">
        <h5><b>Сагласност за спровођење препоручене имунизације</b></h5>
        <svg width="200" height="200" xmlns="http://www.w3.org/2000/svg">
            <image href="../assets/images/batut.png" width="200" height="200" />
        </svg>
    </div>
    <div class="form-group">
        <label for="options">Држављанство</label>
        <select id="options" v-model="drzavljanstvoIzbor" name="notResident" class="form-control" required>
            <option value="1">Република Србија</option>
            <option value="2">Страни држављанин</option>
        </select>
    </div>
    <div v-if="drzavljanstvoIzbor == '1'" class="form-group">
        <label for="jmbg">ЈМБГ</label>
        <input v-model="saglasnost.drzavljanstvo.jmbg" type="text" id="jmbg" class="form-control">
        <div class="invalid-feedback">Невалидан јмбг.</div>
    </div>
    <div v-if="drzavljanstvoIzbor == '2'" class="form-group">
        <label for="jmbg">Назив страног држављанства (*)</label>
        <input v-model="saglasnost.drzavljanstvo.stranoDrzavljanstvo" type="text" id="stranoDrzavljanstvo" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
    <div v-if="drzavljanstvoIzbor == '2'" class="form-group">
        <label for="jmbg">Бр. пасоша или ЕБС за стране држављане</label>
        <input v-model="saglasnost.drzavljanstvo.EBS" type="text" id="ebs" class="form-control">
        <span class="validation-error" data-bind="validationMessage: patientInformation.JMBG" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="prezime">Презиме</label>
        <input v-model="saglasnost.podaciPacijenta.prezime._text" type="text" id="prezime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.lastName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="ime">Име</label>
        <input v-model="saglasnost.podaciPacijenta.ime._text" type="text" id="ime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="ime">Име родитеља</label>
        <input v-model="saglasnost.podaciPacijenta.imeRoditelja" type="text" id="ime" class="form-control" required>
        <span class="validation-error" data-bind="validationMessage: patientInformation.firstName" style="display: none;"></span>
    </div>
    <div class="form-group">
        <label for="pol">Пол</label>
        <div>
            <input v-model="saglasnost.podaciPacijenta.pol._text" type="radio" id="musko" name="pol" value="Мушко">
            <label for="musko">Мушко</label>
        </div>
        <div>
            <input v-model="saglasnost.podaciPacijenta.pol._text" type="radio" id="zensko" name="pol" value="Женско">
            <label for="zensko">Женско</label>
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfBirth">Датум рођења</label>
        <input v-model="saglasnost.podaciPacijenta.datumRodjenja._text" type="date" id="datumRodjenja" class="form-control"  :max="date16YrsAgo" required> 
    </div>
    <div class="form-group">
        <label for="placeOfBirth">Место рођења</label>
        <input v-model="saglasnost.podaciPacijenta.podaciMjesta.mjestoRodjenja" type="text" id="placeOfBirth" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="address">Адреса (улица) </label>
        <input v-model="saglasnost.podaciPacijenta.podaciMjesta.adresa.ulica._text" type="text" id="address" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="broj">Адреса (број) </label>
        <input v-model="saglasnost.podaciPacijenta.podaciMjesta.adresa.broj._text" type="text" id="broj" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="place">Место/Насеље</label>
        <input v-model="saglasnost.podaciPacijenta.podaciMjesta.mjestoNaselje" type="text" id="mjestoNaselje" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="municipality">Општина/Град</label>
        <input v-model="saglasnost.podaciPacijenta.podaciMjesta.opstinaGrad" type="text" id="opstinaGrad" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="homePhoneNumber">Тел. фиксни</label>
        <input v-model="saglasnost.podaciPacijenta.kontaktPodaci.fiksniTelefon._text" type="text" id="fiksni" class="form-control">
    </div>
    <div class="form-group">
        <label for="mobilePhoneNumber">Тел. мобилни</label>
        <input v-model="saglasnost.podaciPacijenta.kontaktPodaci.mobilniTelefon._text" type="text" id="mobilni" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="email">Имејл</label>
        <input v-model="saglasnost.podaciPacijenta.kontaktPodaci.email._text" type="text" id="email" class="form-control" required>
         <div class="invalid-feedback">Невалидан имејл.</div>
    </div>
    <div class="form-group">
        <label for="status">Радни статус</label>
        <div>
            <input type="radio" name="radniStatus" id="zaposlen" value="запослен" @click="onlyOneCheckbox('запослен', 'radniStatus')">
            <label for="status">Запослен</label>
        </div>
        <div>
            <input type="radio" name="radniStatus" id="nezaposlen" value="незапослен" @click="onlyOneCheckbox('незапослен', 'radniStatus')">
            <label for="status">Незапослен</label>
        </div>
        <div>
            <input type="radio"  name="radniStatus" id="penzioner" value="пензионер" @click="onlyOneCheckbox('пензионер', 'radniStatus')">
            <label for="status">Пензионер</label>
        </div>
        <div>
            <input type="radio"  name="radniStatus" id="ucenik" value="ученик" @click="onlyOneCheckbox('ученик', 'radniStatus')">
            <label for="status">Ученик</label>
        </div>
        <div>
            <input type="radio" name="radniStatus" id="student" value="студент" @click="onlyOneCheckbox('студент', 'radniStatus')">
            <label for="status">Студент</label>
        </div>
        <div>
            <input type="radio" name="radniStatus" id="dete" value="дете" @click="onlyOneCheckbox('дете', 'radniStatus')">
            <label for="status">Дете</label>
        </div>
    </div>
    <div v-if="saglasnost.podaciPacijenta.radniStatus === 'запослен'" class="form-group">
        <label for="occupation">Занимање запосленог </label>
        <div>
            <input type="radio" id="zdravstvenaZastita" name="zanimanjeZaposlenog" value="здравствена заштита" @click="onlyOneCheckbox('здравствена заштита', 'zanimanjeZaposlenog')">
            <label for="occupation">Здравствена заштита</label>
        </div>
        <div> 
            <input type="radio" id="socijalnaZastita" name="zanimanjeZaposlenog" value="социјална заштита" @click="onlyOneCheckbox('социјална заштита', 'zanimanjeZaposlenog')">
            <label for="occupation">Социјална заштита</label>
        </div>
        <div>
            <input type="radio" id="prosveta" name="zanimanjeZaposlenog" value="просвјета" @click="onlyOneCheckbox('просвјета', 'zanimanjeZaposlenog')">
            <label for="occupation">Просвета</label>
        </div>
        <div>
            <input type="radio" id="mup" name="zanimanjeZaposlenog" value="МУП" @click="onlyOneCheckbox('МУП', 'zanimanjeZaposlenog')">
            <label for="occupation">МУП</label>
        </div>
        <div>
            <input type="radio" id="vojskaRS" name="zanimanjeZaposlenog" value="Војска РС" @click="onlyOneCheckbox('Војска РС', 'zanimanjeZaposlenog')">
            <label for="occupation">Војска РС</label>
        </div>
        <div>
            <input type="radio" id="drugo" name="zanimanjeZaposlenog" value="друго" @click="onlyOneCheckbox('друго', 'zanimanjeZaposlenog')">
            <label for="occupation">Друго</label>
        </div>
    </div>
    <div class="form-group">
        <label for="socialSecurityBeneficiary">Корисник установе соц. зашт. </label>
        <div>
            <input type="radio" id="da" name="socialSecurityBeneficiary"  v-model="saglasnost.podaciPacijenta.socijalnaZastita.korisnikSocijalnog" value="ДА">
            <label for="socialSecurityBeneficiary">Да</label>
        </div>
        <div>
            <input type="radio" id="ne" name="socialSecurityBeneficiary" v-model="saglasnost.podaciPacijenta.socijalnaZastita.korisnikSocijalnog" value="НЕ">
            <label for="socialSecurityBeneficiary">Не</label>
        </div>
    </div>
    <div v-if="saglasnost.podaciPacijenta.socijalnaZastita.korisnikSocijalnog === 'ДА'" class="form-group">

        <div class="form-group">
            <label for="nazivUstanove">Назив установе</label>
            <input v-model="saglasnost.podaciPacijenta.socijalnaZastita.nazivUstanove" type="text" id="nazivUstanove" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="sjedisteUstanove">Општина седишта</label>
            <input v-model="saglasnost.podaciPacijenta.socijalnaZastita.sjedisteUstanove" type="text" id="sjedisteUstanove" class="form-control" required>
        </div>

    </div>

    <div class="form-group">
        <label for="agreement">Изјављујем да: </label>
        <div>
            <input type="radio" id="saglasan" name="agreement" v-model="saglasnost.podaciPacijenta.izjavaSaglasnosti.saglasan" value="САГЛАСАН САМ">
            <label for="agreement">САГЛАСАН САМ</label>
        </div>
        <div>
            <input type="radio" id="nesaglasan" name="agreement" v-model="saglasnost.podaciPacijenta.izjavaSaglasnosti.saglasan" value="НИСАМ САГЛАСАН">
            <label for="agreement">НИСАМ САГЛАСАН</label>
        </div>
        <div v-if="saglasnost.podaciPacijenta.izjavaSaglasnosti.saglasan === 'САГЛАСАН САМ'">
            <label for="drug">са спровођењем активне/пасивне имунизације (уписати назив имунолошког лека): </label>

            <select id="options" v-model="saglasnost.podaciPacijenta.izjavaSaglasnosti.imeLijeka" name="notResident" class="form-control" required>
                <option value="Pfizer-BioNTech">Pfizer-BioNTech</option>
                <option value="Sputnik V (Gamaleya истраживачки центар)">Sputnik V</option>
                <option value="Sinopharm">Sinopharm</option>
                <option value="AstraZeneca">AstraZeneca</option>
                <option value="Moderna">Moderna</option>
            </select>   
        </div>
    </div>

    <div v-if="saglasnost.podaciPacijenta.izjavaSaglasnosti.saglasan === 'САГЛАСАН САМ'" class="bottom">
        <button class="button secondary" type="submit">Поднеси захтев</button>
    </div>

    <br>

    </div>

    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    <div :style="[user.rola !== 'ZDRAVSTVENI_RADNIK`' ? {'pointer-events': 'none'} : {}]" id="vaccine-interest-form">
    <br><br>

    <div id="title2">
        <h5><b>Евиденција о вакцинацији против COVID-19</b></h5>
        <h6>(Попуњава здравствени радник)</h6>
    </div>

    <br><br>

    <div class="form-group">
        <label for="healthInstitution">Здравствена установа</label>
        <input v-model="saglasnost.zdravstvenaUstanova.nazivZdravstveneUstanove" type="text" id="healthInstitution" class="form-control">
    </div>

    <div class="form-group">
        <label for="healthInstitution">Вакцинацијски пункт</label>
        <input v-model="saglasnost.zdravstvenaUstanova.vakcinacijskiPunkt" type="text" id="healthInstitution" class="form-control">
    </div>


    <div class="form-group">
        <label for="doctorInfo">Име доктора</label>
        <input v-model="saglasnost.podaciLjekara.ime._text" type="text" id="doctorInfo" class="form-control">
    </div>


    <div class="form-group">
        <label for="doctorInfo">Презиме доктора</label>
        <input v-model="saglasnost.podaciLjekara.prezime._text" type="text" id="doctorInfo" class="form-control">
    </div>


    <div class="form-group">
        <label for="doctorInfo">Факсимил доктора</label>
        <input v-model="saglasnost.podaciLjekara.faksimil" type="text" id="doctorInfo" class="form-control">
    </div>


    <div class="form-group">
        <label for="doctorInfo">Број телефона доктора</label>
        <input v-model="saglasnost.podaciLjekara.brojTelefona._text" type="text" id="doctorInfo" class="form-control">
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
            <tr>
                <td><input v-model="saglasnost.podaciVakcinacija.nazivVakcine" type="text" id="vaccineName" class="form-control"></td>
                <td><input v-model="saglasnost.podaciVakcinacija.datumDavanjaVakcine" type="date" id="vaccineDate" class="form-control"></td>
                <td><input v-model="saglasnost.podaciVakcinacija.nacinDavanjaVakcine"></td>
                <td><input v-model="saglasnost.podaciVakcinacija.ekstremitet"></td>
                <td>
                    <input v-model="saglasnost.podaciVakcinacija.serijaVakcine" type="text" id="vaccineLot" class="form-control">
                </td>
                <td>
                    <input v-model="saglasnost.podaciVakcinacija.proizvodjac" type="text" id="vaccineProducer" class="form-control">
                </td>
                <td>
                    <input v-model="saglasnost.podaciVakcinacija.nezeljenaReakcija" type="text" id="vaccineReaction" class="form-control">
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    Привремене контраиндикације (датум утврђивања): 
                    <input v-model="saglasnost.privremeneKontraindikacije.datumUtvrdjivanja" type="date" id="vaccineTemporaryContraindication" class="form-control">
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    Привремене контраиндикације (дијагноза): 
                    <input v-model="saglasnost.privremeneKontraindikacije.dijagnoza" type="text" id="vaccineTemporaryContraindication" class="form-control">
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    Одлука комисије за трајне контраиндикације (ако постоји, уписати Да): 
                    <input v-model="saglasnost.podaciVakcinacija.odlukaKomisije" type="text" id="vaccinePermanentContraindication" class="form-control">
                </td>
            </tr>
        </tbody>
    </table>

    </div>

</form>

</template>

<script>
import ObrazacSaglasnostiService from "@/service/ObrazacSaglasnostiService.js";
import { mapState } from "vuex";
import xmljs from "xml-js";

export default {
    name: "ImmunizationConsenstForm",

    computed: {
        ...mapState([
            'user',
            'brojFiksnogRegex',
            'brojMobilnogRegex',
            'emailRegex'
        ])
    },

    data() {
        return {
            drzavljanstvoIzbor: "1",

            saglasnost: {
                drzavljanstvo: {
                    stranoDrzavljanstvo: "",
                    brojPasosa: "",
                    EBS: "",

                    drzavljanstvoSrbije: "Република Србија",
                    jmbg: ""
                },
                podaciPacijenta: {
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
                    imeRoditelja: "",
                    podaciMjesta: {
                        mjestoRodjenja: "",
                        adresa: {
                            ulica: {
                                "_attributes": {
                                    "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                                },
                                "_text": ""
                            },
                            broj: {
                                "_attributes": {
                                    "xmlns": "http://www.ftn.uns.ac.rs/zajednicka"
                                },
                                "_text": ""
                            }
                        },
                        mjestoNaselje: "",
                        opstinaGrad: "",
                    },
                    kontaktPodaci: {
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
                    },
                    radniStatus: "",
                    zanimanjeZaposlenog: "",
                    socijalnaZastita: {
                        korisnikSocijalnog: "",
                        nazivUstanove: "",
                        sjedisteUstanove: "",
                        nijeKorisnikSocijalnog: ""
                    },
                    izjavaSaglasnosti: {
                        saglasan: "",
                        imeLijeka: "",
                        nesaglasan: ""
                    },
                    datumSaglasnosti: new Date()
                },

                zdravstvenaUstanova: {
                    nazivZdravstveneUstanove: "",
                    vakcinacijskiPunkt: ""
                },

                podaciLjekara: {
                    ime: "",
                    prezime: "",
                    faksimil: "",
                    brojTelefona: ""
                },
                
                podaciVakcinacija: {
                    
                },

                privremeneKontraindikacije: {
                    datumUtvrdjivanja: "",
                    dijagnoza: ""
                },

                odlukaKomisije: ""
            },

            ustanova: "",
            date16YrsAgo: new Date(),

        };

    },

    created() {
        this.saglasnost.podaciPacijenta.ime._text = this.user.ime;
        this.saglasnost.podaciPacijenta.prezime._text = this.user.prezime;

        this.date16YrsAgo.setFullYear(this.date16YrsAgo.getFullYear() - 16);
        let d = this.date16YrsAgo;

        this.date16YrsAgo =  d.getFullYear() +  "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2);
    },

    methods: {
        
        confirm() {
            this.resetInvalidStates();

            if (!this.validateForm())
                return;

            this.saglasnost.podaciPacijenta.datumSaglasnosti= new Date().toISOString().slice(0, 10);

            this.prepareSaglasnost();
            let data = "<saglasnost xmlns='http://www.ftn.uns.ac.rs/obrazac-saglasnosti'>" + xmljs.json2xml(this.saglasnost, {compact: true, spaces: 4}) + "</saglasnost>";
            
            console.log(data);
            ObrazacSaglasnostiService.createObrazacSaglasnosti(data)
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

            // eslint-disable-next-line
            if (!this.emailRegex.test(this.saglasnost.podaciPacijenta.kontaktPodaci.email._text)) {
                this.setInputInvalid(form, "email");
                return false;
            }

            if (!this.brojFiksnogRegex.test(this.saglasnost.podaciPacijenta.kontaktPodaci.fiksniTelefon._text)) {
                this.setInputInvalid(form, "fiksni");
                return false;       
            }

            if (!this.brojMobilnogRegex.test(this.saglasnost.podaciPacijenta.kontaktPodaci.mobilniTelefon._text)) {
                this.setInputInvalid(form, "mobilni");
                return false;
            }

            return true;
        },

        prepareSaglasnost() {
            if (this.saglasnost.drzavljanstvo.jmbg) {
                delete this.saglasnost.drzavljanstvo.stranoDrzavljanstvo;
                delete this.saglasnost.drzavljanstvo.brojPasosa;
                delete this.saglasnost.drzavljanstvo.EBS
            } else {
                delete this.saglasnost.drzavljanstvo.drzavljanstvoSrbije;
                delete this.saglasnost.drzavljanstvo.jmbg;
                delete this.saglasnost.drzavljanstvo.brojPasosa;
            }

            if (this.saglasnost.podaciPacijenta.radniStatus !== "запослен")
                this.saglasnost.podaciPacijenta.zanimanjeZaposlenog = 'друго';

            if (this.saglasnost.podaciPacijenta.socijalnaZastita.korisnikSocijalnog === 'ДА') {
                delete this.saglasnost.podaciPacijenta.socijalnaZastita.nijeKorisnikSocijalnog;
            } else {
                delete this.saglasnost.podaciPacijenta.socijalnaZastita.korisnikSocijalnog;
                delete this.saglasnost.podaciPacijenta.socijalnaZastita.nazivUstanove;
                delete this.saglasnost.podaciPacijenta.socijalnaZastita.sjedisteUstanove;
                this.saglasnost.podaciPacijenta.socijalnaZastita.nijeKorisnikSocijalnog = "НЕ";
            }
    
            if (this.saglasnost.podaciPacijenta.izjavaSaglasnosti.saglasan === 'САГЛАСАН САМ') {
                delete this.saglasnost.podaciPacijenta.izjavaSaglasnosti.nesaglasan;
            } else {
                delete this.saglasnost.podaciPacijenta.izjavaSaglasnosti.saglasan;
                delete this.saglasnost.podaciPacijenta.izjavaSaglasnosti.imeLijeka;
            }

            delete this.saglasnost.zdravstvenaUstanova;
            delete this.saglasnost.podaciLjekara;
            delete this.saglasnost.podaciVakcinacija;
            delete this.saglasnost.privremeneKontraindikacije;
            delete this.saglasnost.odlukaKomisije;
        },

        prepareDrugiDeoSaglasnosti() {
            if (this.saglasnost.odlukaKomisije !== "Да")
                this.saglasnost.odlukaKomisije = "Не";
        },

        setInputInvalid(form, inputId) {
            let inputEl = document.getElementById(inputId);
            inputEl.classList.add("is-invalid");
            form.checkValidity();
        },

        onlyOneCheckbox(checkbox, elementName) {
            this.saglasnost.podaciPacijenta[elementName] = checkbox;
            let checkboxes = document.getElementsByName(elementName)
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
<template>
    <b-container>
        <div class="row1" style="padding-bottom: 50px">
            <div class="column1">
                <div>
                    <label>Одаберите почетни датум</label>
                    <b-form-datepicker v-bind="labels.sr" :locale="'sr'" v-model="fromDate" class="mb-2"></b-form-datepicker>
                </div>
            </div>
            <div class="column1">
                <div>
                    <label>Одаберите крајњи датум</label>
                    <b-form-datepicker v-bind="labels.sr" :locale="'sr'" v-model="toDate" class="mb-2"></b-form-datepicker>
                </div>
            </div>
        </div>
        <b-button block variant="primary" v-on:click="confirm()">Генериши извештај</b-button>
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <div class="table-responsive">
                        <table class="table user-list">

                            <tbody>
                                <tr>
                                    <td><span class="bold-span">Број примљених докумената о интересовању: </span>{{ brojInteresovanja }}</td>
                                </tr><br>
                               <tr>
                                    <td><span class="bold-span">Број издатих дигиталних зелених сертификата: </span></td>
                                </tr>
                                <br>
                                <tr>
                                    <td><span class="bold-span">Број датих вакцина за Pfizer-BioNTech: </span>{{ vakcine.pfizer._text }}</td>
                                </tr>
                                <tr>
                                    <td><span class="bold-span">Број датих вакцина за Sputnik V (Gamaleya истраживачки центар): </span>{{ vakcine.sputnik._text }}</td>
                                </tr>
                                <tr>
                                    <td><span class="bold-span">Број датих вакцина за Sinopharm: </span>{{ vakcine.sinopharm._text }}</td>
                                </tr>
                                <tr>
                                    <td><span class="bold-span">Број датих вакцина за AstraZeneca: </span>{{ vakcine.astra._text }}</td>
                                </tr>
                                <tr>
                                    <td><span class="bold-span">Број датих вакцина за Moderna: </span> {{ vakcine.moderna._text }} </td>
                                </tr><br>
                                <tr>
                                    <td><span class="bold-span">Укупан број датих вакцина: </span>{{ vakcineUkupno }}</td>
                                </tr><br>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
        <pdf v-if="showPdf" :src="pdfsrc" ></pdf>

    </b-container>
</template>

<script>
import InteresovanjeService from "@/service/InteresovanjeService.js";
import VakcinaService from "@/service/VakcinaService.js";
import xmljs from "xml-js";

import pdf from "vue-pdf"

export default {
    name: "ImmunizationReport",

    components: { pdf },

    data() {
        return {
            fromDate: "",
            toDate: "",
            labels: {
                sr: {
                    labelPrevDecade: 'Прошли век',
                    labelPrevYear: 'Прошла година',
                    labelPrevMonth: 'Прошли месец',
                    labelCurrentMonth: 'Тренутни месец',
                    labelNextMonth: 'Следећи месец',
                    labelNextYear: 'Следећа година',
                    labelNextDecade: 'Следећа деценија',
                    labelToday: 'Данас',
                    labelSelected: 'Одабрани датум',
                    labelNoDateSelected: 'Није одабран датум',
                    labelCalendar: 'Календар',
                    labelNav: 'Календар навигација',
                    labelHelp: 'Користите стрелице за навигацију кроз календар'
                }
            },
            pdfsrc: null,
            showPdf: true,

            brojInteresovanja: "",
            vakcine: {
                "pfizer": "",
                "astra": "",
                "moderna": "",
                "sputnik": "",
                "sinopharm": ""
            },
            vakcineUkupno: ""
        };
    },

    methods: {
        confirm() {
            InteresovanjeService.getBrojInteresovanja(this.fromDate, this.toDate)
            .then(response => {
                this.brojInteresovanja = response.data;
            });

            VakcinaService.getBrojVakcina(this.fromDate, this.toDate)
            .then(response => {
                let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                this.vakcine = data.vakcinaKolicina;
                this.vakcineUkupno = parseInt(this.vakcine.pfizer._text) + parseInt(this.vakcine.sinopharm._text) + parseInt(this.vakcine.sputnik._text) + parseInt(this.vakcine.astra._text) + parseInt(this.vakcine.moderna._text);
            })


        },
        generisiIzvjestaj() {
            console.log(this.fromDate);
            console.log(this.toDate);
            // IzvjestajService.generisiNoviIzvjestaj(this.fromDate, this.toDate);
        }
    },
};
</script>

<style scoped>

.row1 {
    display: flex;
}

.column1 {
    flex: 50%;
    padding: 10px;
    height: 50px;
}
.row {
    margin-top: 30px;
}

.bold-span {
    font-weight: bold;
}

</style>
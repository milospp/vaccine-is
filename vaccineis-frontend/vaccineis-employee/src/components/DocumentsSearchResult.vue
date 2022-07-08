<template>
    <div class="container">

        <div class="sort-filter">
            <h3>Филтер: </h3>
            <b-form-select v-model="filterSelected" :options="filterOptions" style="width: 30%"/>
        </div>

        <div class="row card-group">
            <div class="col-3 mb-3" v-for="(doc,index) in naprednaList" :key="index">
                <div class="card">
                    <!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                    <router-link class="routerLink" :to="{}">
                        <small class="p-2">{{index}}</small>

                        <img class="card-img-top" src="@/assets/images/pharmacy.png" alt="Card image cap" v-on:click="viewIzvjestajPdf()">
                        <div class="card-header">
                            <div v-for="(v,k) in doc" :key="k">
                                <strong>{{k}}: </strong><span>{{v}}</span>
                            </div>
                            <!-- <b>{{ doc }}</b> -->
                        </div>
                    </router-link>
                    <div class="card-body">
                        <a v-on:click="getDocPdf(index)" class="card-link">PDF</a>
                        <a v-on:click="getDocHtml(index)" class="card-link">XHTML</a>
                        <a v-on:click="getDocRdf(index)" class="card-link">RDF</a>
                        <a v-on:click="getDocJson(index)" class="card-link">JSON</a>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import IzvjestajService from '@/service/IzvjestajService';

import InteresovanjeService from '@/service/InteresovanjeService';
import DigitalniZeleniSertifikatService from "@/service/DigitalniZeleniSertifikatService";
import ObrazacSaglasnostiService from "@/service/ObrazacSaglasnostiService";
import ZahtjevZaSertifikatService from "@/service/ZahtjevZaSertifikatService";
import PotvrdaVakcinacijeService from "@/service/PotvrdaVakcinacijeService";

import {mapState} from 'vuex'

export default {
    name: "DocumentsSearchResult",



    data() {
        return {
            documents: [
                {
                    name: "Saglasnost neka"
                },
                {
                    name: "Saglasnost druga"
                },
                {
                    name: "Saglasnost druga"
                },
                {
                    name: "Saglasnost druga"
                },
                {
                    name: "Saglasnost druga"
                },
                {
                    name: "Saglasnost druga"
                }
            ],
            filterSelected: "sve",
            filterOptions: [
                { value: 'sve', text: 'Све' },
                { value: 'interesovanje', text: 'Интересовање за пријем вакцине' },
                { value: 'saglasnost', text: 'Сагласност за спровођење имунизавије' },
                { value: 'zahtev', text: 'Захтев за дигитални зелени сертификат' },
                { value: 'digitalni', text: 'Дигитални зелени сертификат' }
            ],
            pdfsrc: null
        };
    },

    computed: {
        ...mapState([
            'naprednaList',
        ]),

    },


    methods: {
        filterDocuments() {
        },

        getIzvjestajPdf() {
            IzvjestajService.getIzvjestajPdf();
        },

        getIzvjestajHtml() {
            IzvjestajService.getIzvjestajHtml();
        },

        viewIzvjestajPdf() {
            IzvjestajService.viewIzvjestajPdf().then((response) => {
                var fileUrl = URL.createObjectURL(response.data);
                window.open(fileUrl);
            });
        },

        getTypeAndId(uri) {
            let uriToken = uri.split("/");

            let docType = uriToken[uriToken.length -2]
            let uuid = uriToken[uriToken.length - 1]

            return [docType, uuid];
        },

        getDocPdf(uri) {
            console.log(uri);
            let [uriType, uuid] = this.getTypeAndId(uri);

            if (uriType === "interesovanje") InteresovanjeService.getInteresovanjePdf(uuid);
            else if (uriType === "saglasnost") ObrazacSaglasnostiService.getSaglasnostPdf(uuid);
            else if (uriType === "zahtev") ZahtjevZaSertifikatService.getZahtevPdf(uuid);
            else if (uriType === "digitalni-sertifikat") DigitalniZeleniSertifikatService.getSertifikatPdf(uuid);
            else if (uriType === "potvrda") PotvrdaVakcinacijeService.getPotvrdaPdf(uuid);
        },

        getDocHtml(uri) {

            let [uriType, uuid] = this.getTypeAndId(uri);

            if (uriType === "interesovanje") InteresovanjeService.getInteresovanjeHtml(uuid);
            else if (uriType === "saglasnost") ObrazacSaglasnostiService.getSaglasnostHtml(uuid);
            else if (uriType === "zahtev") ZahtjevZaSertifikatService.getZahtevHtml(uuid);
            else if (uriType === "digitalni-sertifikat") DigitalniZeleniSertifikatService.getSertifikatHtml(uuid);
            else if (uriType === "potvrda") PotvrdaVakcinacijeService.getPotvrdaHtml(uuid);
        },

        getDocRdf(uri) {

            let [uriType, uuid] = this.getTypeAndId(uri);

            if (uriType === "interesovanje") InteresovanjeService.getInteresovanjeRdf(uuid);
            else if (uriType === "saglasnost") ObrazacSaglasnostiService.getSaglasnostRdf(uuid);
            else if (uriType === "zahtev") ZahtjevZaSertifikatService.getZahtevRdf(uuid);
            else if (uriType === "digitalni-sertifikat") DigitalniZeleniSertifikatService.getSertifikatRdf(uuid);
            else if (uriType === "potvrda") PotvrdaVakcinacijeService.getPotvrdaRdf(uuid);
        },

        getDocJson(uri) {

            let [uriType, uuid] = this.getTypeAndId(uri);

            if (uriType === "interesovanje") InteresovanjeService.getInteresovanjeJson(uuid);
            else if (uriType === "saglasnost") ObrazacSaglasnostiService.getSaglasnostJson(uuid);
            else if (uriType === "zahtev") ZahtjevZaSertifikatService.getZahtevJson(uuid);
            else if (uriType === "digitalni-sertifikat") DigitalniZeleniSertifikatService.getSertifikatJson(uuid);
            else if (uriType === "potvrda") PotvrdaVakcinacijeService.getPotvrdaJson(uuid);
        }

    },
}
</script>

<style scoped>

.card-body {
    padding: 1rem;
}

.routerLink {
    text-decoration: none;
}
img {
    height: 10%
}
.container {
    margin-top: 2%;
}
.row {
    margin-top: 2%;
    margin-bottom: 5%;
}
.card {
    text-align: left;
}
p {
    margin: 0
}
/* Styles for wrapping the search box */
.main {
    width: 50%;
    margin: 50px auto;
}
.filter {
    text-align: left;
}
a.card-link {
    cursor: pointer;
}
</style>
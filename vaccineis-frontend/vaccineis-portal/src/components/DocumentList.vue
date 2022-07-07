<template>
<div class="container">

    <div class="sort-filter">
        <h3>Филтер: </h3>
        <select class="filter-select" v-model="filterData" @change=filterDocuments($event)>
            <option disabled value="">Изабери критеријум</option>
            <option value="city-ascending">Интересовање за пријем вакцине</option>
            <option value="city-descending">Сагласност за спровођење имунизавије</option>
            <option value="digital-green-certificate-request">Захтев за дигитални зелени сертификат</option>
            <option value="digital-green-certificate">Дигитални зелени сертификат</option>
        </select>
    </div>

    <div class="row card-group">
        <div class="col-3" v-for="doc in docsObr" :key="doc.id._text">
            <div class="card">
<!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                <router-link class="routerLink" :to="{}">
                    <img class="card-img-top" src="@/assets/images/dok.png" alt="Card image cap" v-on:click="viewInteresovanjePdf(doc.id._text)">
                    <div class="card-header"><b>{{ doc.naziv._text }} {{fromatDateTime(doc.datum._text)}}</b></div>
                </router-link>
                <div class="card-body">
                    <a v-on:click="getInteresovanjePdf(doc.id._text)" class="card-link">PDF</a>
                    <a v-on:click="getInteresovanjeHtml(doc.id._text)" class="card-link">XHTML</a>
                    <a href="#" class="card-link">RDF</a>
                    <a href="#" class="card-link">JSON</a>
                </div>
            </div>
        </div>
    </div>

</div>
</template>

<script>
import InteresovanjeService from '@/service/InteresovanjeService';
import DigitalniZeleniSertifikatService from "@/service/DigitalniZeleniSertifikatService";
import ObrazacSaglasnostiService from "@/service/ObrazacSaglasnostiService";
import ZahtjevZaSertifikatService from "@/service/ZahtjevZaSertifikatService";
import PotvrdaVakcinacijeService from "@/service/PotvrdaVakcinacijeService";

import xmljs from "xml-js";

export default {
    name: "DocumentList",

    data() {
        return {
            docs: [],
            docsInt: [],
            docsObr: [],
            docsZah: [],
            docsDig: [],
            docsPot: [],

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

            filterData: "city-ascending",
        };
    },

    computed: {
        filtered() {
            return [];
        }
    },

    methods: {
        filterDocuments() {
        },

        getInteresovanjePdf(uuid) {
          InteresovanjeService.getInteresovanjePdf(uuid);
        },

        getInteresovanjeHtml(uuid) {
          InteresovanjeService.getInteresovanjeHtml(uuid);
        },

        viewInteresovanjePdf(uuid) {
          InteresovanjeService.viewInteresovanjePdf(uuid);
        },

        viewSertifikatPdf() {
            DigitalniZeleniSertifikatService.viewSertifikatPdf();
        },

        getDocs() {
            InteresovanjeService.mojaInteresovanja().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(data);
                    this.docsInt = data;
                }
            });

            ObrazacSaglasnostiService.mojiObrasci().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(data);
                    this.docsObr = data;

                }
            });

            ZahtjevZaSertifikatService.mojiZahtjevi().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(data);
                    this.docsZah = data;

                }
            });

            DigitalniZeleniSertifikatService.mojiDigitalniSertifikati().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(data);
                    this.docsDig = data;
                }
            });

            PotvrdaVakcinacijeService.mojePotvrde().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(data);
                    this.docsPot = data;
                    
                }
            });
        },
        
        fromatDate(string) {
            let dateObj = new Date(string);
            return dateObj.toLocaleDateString('sr-RS');
        },

        fromatTime(string) {
            let dateObj = new Date(string);
            return dateObj.toLocaleTimeString('sr-RS').slice(0,5);
        },

        fromatDateTime(string) {
            return this.fromatDate(string) + " " + this.fromatTime(string);
        }
    },

    created() {
        this.getDocs();
    },
};
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

</style>
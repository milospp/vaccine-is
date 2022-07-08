<template>
<div class="container">

    <div class="sort-filter">
        <h3>Филтер: </h3>
        <select class="filter-select" v-model="filterData" @change=filterDocuments($event)>
            <option disabled value="">Изабери критеријум</option>
            <option value="all">Све</option>
            <option value="interesovanje">Интересовање за пријем вакцине</option>
            <option value="obrazac">Сагласност за спровођење имунизавије</option>
            <option value="zahtev">Захтев за дигитални зелени сертификат</option>
            <option value="zeleni">Дигитални зелени сертификат</option>
            <option value="potvrda">Потврда вакцинације</option>
        </select>
    </div>

    <div class="row card-group">
        <template v-if="filterData === 'interesovanje' || filterData === 'all' || filterData === ''">
            <div class="col-3" v-for="doc in docsInt" :key="doc.id._text">
                <div class="card">
    <!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                    <router-link class="routerLink" :to="{}">
                        <img class="card-img-top" src="@/assets/images/dok.png" alt="Card image cap" v-on:click="viewDoc('interesovanje', doc.id._text)">
                        <div class="card-header"><b>{{ doc.naziv._text }} {{fromatDateTime(doc.datum._text)}}</b></div>
                    </router-link>
                    <div class="card-body">
                        <a v-on:click="getDocPdf('interesovanje', doc.id._text)" class="card-link">PDF</a>
                        <a v-on:click="getDocHtml('interesovanje', doc.id._text)" class="card-link">XHTML</a>
                        <a v-on:click="getDocRdf('interesovanje', doc.id._text)" class="card-link">RDF</a>
                        <a v-on:click="getDocJson('interesovanje', doc.id._text)" class="card-link">JSON</a>
                    </div>
                </div>
            </div>
        </template>
        <template v-if="filterData === 'obrazac' || filterData === 'all' || filterData === ''">
            <div class="col-3" v-for="(doc,index) in docsObr" :key="index">
                <div class="card">
    <!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                    <router-link class="routerLink" :to="{}">
                        <img class="card-img-top" src="@/assets/images/dok.png" alt="Card image cap" v-on:click="viewDoc('obrazac', doc.id._text)">
                        <div class="card-header"><b>{{ doc.naziv._text }} {{fromatDateTime(doc.datum._text)}}</b></div>
                    </router-link>
                    <div class="card-body">
                        <a v-on:click="getDocPdf('obrazac', doc.id._text)" class="card-link">PDF</a>
                        <a v-on:click="getDocHtml('obrazac', doc.id._text)" class="card-link">XHTML</a>
                        <a v-on:click="getDocRdf('obrazac', doc.id._text)" class="card-link">RDF</a>
                        <a v-on:click="getDocJson('obrazac', doc.id._text)" class="card-link">JSON</a>
                    </div>
                </div>
            </div>
        </template>
        <template v-if="filterData === 'zahtev' || filterData === 'all' || filterData === ''">
            <div class="col-3" v-for="doc in docsZah" :key="doc.id._text">
                <div class="card">
    <!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                    <router-link class="routerLink" :to="{}">
                        <img class="card-img-top" src="@/assets/images/dok.png" alt="Card image cap" v-on:click="viewDoc('zahtev', doc.id._text)">
                        <div class="card-header"><b>{{ doc.naziv._text }} {{fromatDateTime(doc.datum._text)}}</b></div>
                    </router-link>
                    <div class="card-body">
                        <a v-on:click="getDocPdf('zahtev', doc.id._text)" class="card-link">PDF</a>
                        <a v-on:click="getDocHtml('zahtev', doc.id._text)" class="card-link">XHTML</a>
                        <a v-on:click="getDocRdf('zahtev', doc.id._text)" class="card-link">RDF</a>
                        <a v-on:click="getDocJson('zahtev', doc.id._text)" class="card-link">JSON</a>
                    </div>
                </div>
            </div>
        </template>
        <template v-if="filterData === 'zeleni' || filterData === 'all' || filterData === ''">
            <div class="col-3" v-for="doc in docsDig" :key="doc.id._text">
                <div class="card">
    <!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                    <router-link class="routerLink" :to="{}">
                        <img class="card-img-top" src="@/assets/images/dok.png" alt="Card image cap" v-on:click="viewDoc('zeleni', doc.id._text)">
                        <div class="card-header"><b>{{ doc.naziv._text }} {{fromatDateTime(doc.datum._text)}}</b></div>
                    </router-link>
                    <div class="card-body">
                        <a v-on:click="getDocPdf('zeleni', doc.id._text)" class="card-link">PDF</a>
                        <a v-on:click="getDocHtml('zeleni', doc.id._text)" class="card-link">XHTML</a>
                        <a v-on:click="getDocRdf('zeleni', doc.id._text)" class="card-link">RDF</a>
                        <a v-on:click="getDocJson('zeleni', doc.id._text)" class="card-link">JSON</a>
                    </div>
                </div>
            </div>
        </template>
        <template v-if="filterData === 'potvrda' || filterData === 'all' || filterData === ''">
            <div class="col-3" v-for="doc in docsPot" :key="doc.id._text">
                <div class="card">
    <!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                    <router-link class="routerLink" :to="{}">
                        <img class="card-img-top" src="@/assets/images/dok.png" alt="Card image cap" v-on:click="viewDoc('potvrda', doc.id._text)">
                        <div class="card-header"><b>{{ doc.naziv._text }} {{fromatDateTime(doc.datum._text)}}</b></div>
                    </router-link>
                    <div class="card-body">
                        <a v-on:click="getDocPdf('potvrda', doc.id._text)" class="card-link">PDF</a>
                        <a v-on:click="getDocHtml('potvrda', doc.id._text)" class="card-link">XHTML</a>
                        <a v-on:click="getDocRdf('potvrda', doc.id._text)" class="card-link">RDF</a>
                        <a v-on:click="getDocJson('potvrda', doc.id._text)" class="card-link">JSON</a>
                    </div>
                </div>
            </div>
        </template>
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

            filterData: "",
        };
    },

    computed: {
        filterResult() {
            switch (this.filterData) {
                case 'all':
                    return this.docs
                case 'interesovanje':
                    return this.docsInt
                case 'obrazac':
                    return this.docsObr
                case 'zahtev':
                    return this.docsZah
                case 'zeleni':
                    return this.docsDig
                case 'potvrda':
                    return this.docsPot
                default:
                    return this.docs
            }
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
            let self = this;
            InteresovanjeService.mojaInteresovanja().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!data) return;
                    if (!(data instanceof Array)) data = [data];

                    self.docs.push.apply(self.docs, data);
                    self.docsInt = [...data];
                }
            });

            ObrazacSaglasnostiService.mojiObrasci().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!data) return;
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(this.docs, data);
                    this.docsObr = [...data];

                }
            });

            ZahtjevZaSertifikatService.mojiZahtjevi().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!data) return;
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(this.docs, data);
                    this.docsZah = [...data];

                }
            });

            DigitalniZeleniSertifikatService.mojiDigitalniSertifikati().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!data) return;
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(this.docs, data);
                    this.docsDig = [...data];
                }
            });

            PotvrdaVakcinacijeService.mojePotvrde().then(response => {
                if(response.data) {
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    console.log(data);
                    data = data['docDatas']['docData'];
                    if (!data) return;
                    if (!(data instanceof Array)) data = [data];

                    this.docs.push.apply(this.docs, data);
                    this.docsPot = [...data];
                    
                }
            });
        },
        getDocPdf(docs, uuid) {
            if (docs === "interesovanje") InteresovanjeService.getInteresovanjePdf(uuid);
            if (docs === "obrazac") ObrazacSaglasnostiService.getSaglasnostPdf(uuid);
            if (docs === "zahtev") ZahtjevZaSertifikatService.getZahtevPdf(uuid);
            if (docs === "zeleni") DigitalniZeleniSertifikatService.getSertifikatPdf(uuid);
            if (docs === "potvrda") PotvrdaVakcinacijeService.getPotvrdaPdf(uuid);

        },
        getDocHtml(docs, uuid) {
            if (docs === "interesovanje") InteresovanjeService.getInteresovanjeHtml(uuid);
            if (docs === "obrazac") ObrazacSaglasnostiService.getSaglasnostHtml(uuid);
            if (docs === "zahtev") ZahtjevZaSertifikatService.getZahtevHtml(uuid);
            if (docs === "zeleni") DigitalniZeleniSertifikatService.getSertifikatHtml(uuid);
            if (docs === "potvrda") PotvrdaVakcinacijeService.getPotvrdaHtml(uuid);            

        },

        getDocRdf(docs, uuid) {
            if (docs === "interesovanje") InteresovanjeService.getInteresovanjeRdf(uuid);
            if (docs === "obrazac") ObrazacSaglasnostiService.getSaglasnostRdf(uuid);
            if (docs === "zahtev") ZahtjevZaSertifikatService.getZahtevRdf(uuid);
            if (docs === "zeleni") DigitalniZeleniSertifikatService.getSertifikatRdf(uuid);
            if (docs === "potvrda") PotvrdaVakcinacijeService.getPotvrdaRdf(uuid);
        },

        getDocJson(docs, uuid) {
            if (docs === "interesovanje") InteresovanjeService.getInteresovanjeJson(uuid);
            if (docs === "obrazac") ObrazacSaglasnostiService.getSaglasnostJson(uuid);
            if (docs === "zahtev") ZahtjevZaSertifikatService.getZahtevJson(uuid);
            if (docs === "zeleni") DigitalniZeleniSertifikatService.getSertifikatJson(uuid);
            if (docs === "potvrda") PotvrdaVakcinacijeService.getPotvrdaJson(uuid);
        },
        
        viewDoc(docs, uuid) {
            if (docs === "interesovanje") InteresovanjeService.viewInteresovanjePdf(uuid);
            if (docs === "obrazac") ObrazacSaglasnostiService.viewSaglasnostPdf(uuid);
            if (docs === "zahtev") ZahtjevZaSertifikatService.viewZahtevPdf(uuid);
            if (docs === "zeleni") DigitalniZeleniSertifikatService.viewSertifikatPdf(uuid);
            if (docs === "potvrda") PotvrdaVakcinacijeService.viewPotvrdaPdf(uuid);        
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

a.card-link {
    cursor: pointer;
}
</style>
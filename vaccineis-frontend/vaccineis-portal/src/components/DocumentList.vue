<template>
<div class="container">

    <div class="sort-filter">
        <h3>Филтер: </h3>
        <select class="filter-select" @change=filterDocuments($event)>
            <option disabled value="">Изабери критеријум</option>
            <option value="city-ascending">Интересовање за пријем вакцине</option>
            <option value="city-descending">Сагласност за спровођење имунизавије</option>
            <option value="digital-green-certificate-request">Захтев за дигитални зелени сертификат</option>
            <option value="digital-green-certificate">Дигитални зелени сертификат</option>
        </select>
    </div>

    <div class="row card-group" v-for="n in Math.ceil(documents.length/4)" :key="n.id">
        <div class="col-3" v-for="doc in documents.slice((n-1)*4, n*4)" :key="doc.id">
            <div class="card">
<!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                <router-link class="routerLink" :to="{}">
                    <img class="card-img-top" src="@/assets/images/dok.png" alt="Card image cap" v-on:click="viewInteresovanjePdf()">
                    <div class="card-header"><b>{{ doc.name }}</b></div>
                </router-link>
                <div class="card-body">
                    <a v-on:click="getInteresovanjePdf()" class="card-link">PDF</a>
                    <a v-on:click="getInteresovanjeHtml()" class="card-link">XHTML</a>
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

export default {
    name: "DocumentList",

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
            ]
        };
    },

    methods: {
        filterDocuments() {
        },

        getInteresovanjePdf() {
          InteresovanjeService.getInteresovanjePdf();
        },

        getInteresovanjeHtml() {
          InteresovanjeService.getInteresovanjeHtml();
        },

        viewInteresovanjePdf() {
          InteresovanjeService.viewInteresovanjePdf();
        }
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
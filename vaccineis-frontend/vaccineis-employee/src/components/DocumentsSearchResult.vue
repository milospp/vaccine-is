<template>
    <div class="container">

        <div class="sort-filter">
            <h3>Филтер: </h3>
            <b-form-select v-model="filterSelected" :options="filterOptions" style="width: 30%"/>
        </div>

        <div class="row card-group" v-for="n in Math.ceil(documents.length/4)" :key="n.id">
            <div class="col-3" v-for="doc in documents.slice((n-1)*4, n*4)" :key="doc.id">
                <div class="card">
                    <!--                <router-link class="routerLink" :to="{ name: 'Home'}">-->
                    <router-link class="routerLink" :to="{}">
                        <img class="card-img-top" src="@/assets/images/pharmacy.png" alt="Card image cap" v-on:click="viewIzvjestajPdf()">
                        <div class="card-header"><b>{{ doc.name }}</b></div>
                    </router-link>
                    <div class="card-body">
                        <a v-on:click="getIzvjestajPdf()" class="card-link">PDF</a>
                        <a v-on:click="getIzvjestajHtml()" class="card-link">XHTML</a>
                        <a href="#" class="card-link">RDF</a>
                        <a href="#" class="card-link">JSON</a>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import IzvjestajService from '@/service/IzvjestajService';

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
</style>
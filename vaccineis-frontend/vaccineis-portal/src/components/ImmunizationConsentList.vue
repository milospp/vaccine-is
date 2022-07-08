<template>
<div class="container">

    <div class="row card-group">
        <div class="col-3" v-for="doc in documents" :key="doc.id._text">
            <div class="card">
                <router-link class="routerLink" :to="{ name: 'ImmunizationConsentRequestPart2', params: {id: doc.id._text} }">
                    <img class="card-img-top" src="@/assets/images/dok.png" alt="Card image cap" >
                    <div class="card-header"><b>{{ doc["ns2:podaciPacijenta"]["ime"]["_text"] }} {{fromatDate(doc["ns2:podaciPacijenta"]["ns2:datumSaglasnosti"]["_text"])}}</b></div>
                </router-link>
            </div>
        </div>
    </div>

</div>
</template>

<script>
import ObrazacSaglasnostiService from "@/service/ObrazacSaglasnostiService";

import xmljs from "xml-js";

export default {
    name: "ImmunizationConsentList",

    data() {
        return {
            documents: [],
        };
    },

    computed: {
    },

    created() {
        this.getPodnetiObrasciSaglasnosti();
    },

    methods: {

        getPodnetiObrasciSaglasnosti() {
            ObrazacSaglasnostiService.getPodnetiObrasciSaglasnosti()
            .then(response => {
                let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                data = data["ns2:listasaglasnosti"]
                console.log("data: ", data);
                if (!("ns2:saglasnosti" in data))
                    return;
                    
                this.documents = []
                if (data["ns2:saglasnosti"].length)
                    this.documents = data["ns2:saglasnosti"];
                else {
                    this.documents = [];
                    this.documents.push(data["ns2:saglasnosti"]);
                }
                
            }).catch(error => {
                console.log(error.response.data.message) 
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
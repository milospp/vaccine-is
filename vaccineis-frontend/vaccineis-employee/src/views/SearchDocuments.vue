<template>
    <div>
        <Navbar />
        <b-container>
            <b-container >
                <div style="padding-bottom: 20px;" class="search-container">
                    <h5>Основна претрага</h5>
                    <b-input-group class="mt-0" style="padding-left: 15px">
                        <b-form-input v-model="basicSearchInput" placeholder="Претрага.."></b-form-input>
                        <b-button variant="info" v-on:click="doBasicSearch">Претражи</b-button>
                    </b-input-group>
                </div>
                <napredna-pretraga/>
            </b-container>

            <DocumentsSearchResult/>
        </b-container>
    </div>
</template>

<script>
import axios from 'axios';
import xmljs from "xml-js";
import NaprednaPretraga from "@/components/NaprednaPretraga";
import Navbar from "@/components/Navbar";
import DocumentsSearchResult from "@/components/DocumentsSearchResult";

export default {
    name: "SearchDocuments",
    components: {DocumentsSearchResult, NaprednaPretraga, Navbar },
    data() {
        return {
            basicSearchInput: ""
        }
    },
    methods: {
        doBasicSearch() {

            let query = this.getQueryForBasicSearch()
            
            console.log(query);
            this.fetchSearch(query);
        },

        getQueryForBasicSearch() {
            let tokens = this.basicSearchInput.split(" ");

            let filterQueryTokens = []

            tokens.forEach(element => {
                filterQueryTokens.push("?o = \"" + element + "\"")
            });

            let filterQuery = filterQueryTokens.join(" || ")

            return filterQuery;
        },

        async fetchSearch(query) {
            let response = await axios({
                method: 'GET',
                url: `http://localhost:8081/api/izvestaj/napredna`,
                headers: {
                    'Content-Type': 'application/xml'
                },
                params: {'query': query }
            });

            this.$store.dispatch('setNaprednaList', {});


            let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
            console.log(data);

            let sparqlResults = await data["sparql"]?.["results"]?.["result"]
            if (!sparqlResults) return;
            let subjects = {}

            sparqlResults.forEach(result => {
                let id = result.binding[0].uri._text;
                if (!subjects[id]) subjects[id] = {}
                
                let preds = result.binding[1].uri._text.split("/")
                let pred = preds[preds.length - 1]

                let obj = result.binding[2].literal._text;

                subjects[id][pred] = obj

            });

            this.$store.dispatch('setNaprednaList', subjects);

        }
    }
}
</script>

<style scoped>

input[type=text] {
    padding: 6px;
    margin-top: 8px;
    font-size: 17px;
}

.search-container button {
    padding: 6px 10px;
    margin-top: 8px;
    margin-right: 16px;
    font-size: 17px;
    cursor: pointer;
}
</style>
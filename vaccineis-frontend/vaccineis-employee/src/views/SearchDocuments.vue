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
            console.log(this.basicSearchInput);
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
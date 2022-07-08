<template>
    <b-container>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Датум</th>
                    <th scope="col">Име подносиоца</th>
                    <th scope="col">Презиме подносиоца</th>
                    <th scope="col">Преглед докумената</th>
                    <th scope="col" style="border-left: 1px solid black; text-align: center" colspan="2"> Одлука </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="zahtev in zahtevi" :key="zahtev.datum">
                    <td>{{ zahtev['ns2:mjestoDatum']['ns2:datum']._text }}</td>
                    <td>{{ zahtev['ns2:podnosilac']['ime']._text }}</td>
                    <td>{{ zahtev['ns2:podnosilac']['prezime']._text }}</td>
                    <td><button class="btn btn-info" v-on:click="viewDocs()">Прегледај документе</button></td>
                    <td style="border-left: 1px solid black; padding-left: 30px"><button class="btn btn-primary" v-on:click="acceptRequest">Прихвати захтев</button></td>
                    <td><button @click="odbij(zahtev['id'])" class="btn btn-primary" style="background-color: red" v-b-modal.modal>Одбиј захтев</button></td>
                </tr>
            </tbody>
        </table>
        <b-modal id="modal" centered scrollable size="lg" title="Разлог одбијања захтева (садржај мејла)" @ok="declineRequest">
            <b-form-textarea
                id="textarea-default"
                placeholder="Default textarea"
                v-model="declineReason"
            ></b-form-textarea>
        </b-modal>
    </b-container>
</template>

<script>
import DigitalniSertifikatService from "@/service/DigitalniSertifikatService";
import DigitalniZeleniertifikatService from "@/service/DigitalniZeleniSertifikatService";
import xmljs from "xml-js";

export default {
    name: "DigitalGreenCertificateRequestsTable",

    data() {
        return {
            zahtevi: [],
            declineReason: ""
        };
    },

    methods: {
        viewDocs() {
            let route = this.$router.resolve({path: "/преглед-докумената/пацијент/52ad5ae6-10d6-4003-833a-07434c2aba0b"});
            window.open(route.href);
        },

        acceptRequest() {
            // slanje mejla sa sadrzajem o prihvatanju zahtjeva za digitalni..
            DigitalniSertifikatService.respondToSertikatRequest("prihvaceno", "");
        },

        declineRequest() {
            // slanje mejla sa declineReason u sadrzaju
            console.log(this.declineReason);
            DigitalniSertifikatService.respondToSertikatRequest("odbijeno", this.declineReason);
        },

        odbij(id) {
            this.zahtevi = this.zahtevi.filter(x => {
                return x['id'] != id;
            })
        }
    },

    created() {
        DigitalniZeleniertifikatService.getPodnetiSertifikati()
        .then(response => {
            let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
            console.log(data['ns2:listaZahteva']);
            if (data['ns2:listaZahteva']['ns2:zahtevi'].length) {
                this.zahtevi = data['ns2:listaZahteva']['ns2:zahtevi']
            } else {
                this.zahtevi = [];
                this.zahtevi.push(data['ns2:listaZahteva']['ns2:zahtevi']);
            }
        })
    }
};
</script>

<style scoped>
table {
    margin-top: 2rem;
}

</style>
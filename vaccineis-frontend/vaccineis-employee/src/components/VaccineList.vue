<template>
<div class="container">

    <!-- Modal -->
    <div class="modal fade" id="addAmountModal" tabindex="-1" role="dialog" aria-labelledby="addAmountModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addAmountModalLabel">Унос нових вакцина</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <label for="vaccine">
                        <span>Тип вакцине:</span>
                        <input disabled v-model="tip" id="vaccine" type="text">
                    </label>
                    <label for="currentAmount">
                        <span>Тренутна количина:</span>
                        <input disabled v-model="kolicina" id="currentAmount" type="number" min="0">
                    </label>
                    <label for="amount">
                        <span>Количина за додавање:</span>
                        <input v-model="vakcinaKolicina.kolicina" id="amount" type="number" min="0">
                    </label>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Затвори</button>
                <button @click="confirm" type="button" class="btn btn-primary">Потврди</button>
            </div>
        </div>
    </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="main-box clearfix">
                <div class="table-responsive">
                    <table class="table user-list">
                        <thead>
                            <tr>
                                <th><span>Тип вакцине</span></th>
                                <th><span>Количина</span></th>
                                <th><span></span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="vaccine in vaccines" :key="vaccine.id">
                                <td><span>{{ vaccine.tip }}</span></td>
                                <td><span>{{ vaccine.kolicina }}</span></td>
                                <td><button @click="addAmount(vaccine)" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAmountModal">Додај количину</button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import VakcinaService from "@/service/VakcinaService.js";
import xmljs from "xml-js";
import axios from "axios"

export default {
    name: "VaccineList",

    data() {
        return {
            vaccines: [
                {
                    tip: "Phizer, BioNTech",
                    kolicina: 5
                },
                {
                    tip: "Sinopharm",
                    kolicina: 300
                },
                {
                    tip: "Sputnik V",
                    kolicina: 17
                },
                {
                    tip: "AstraZeneca",
                    kolicina: 5
                }
            ],

            tip: "",
            kolicina: "",
            
            vakcinaKolicina: {
                nazivVakcine: "",
                kolicina: 0,
            }
        };
    },

    methods: {

        addAmount(vaccine) {
            this.tip = vaccine.tip;
            this.kolicina = vaccine.kolicina;
        },

        confirm() {
            this.vakcinaKolicina.nazivVakcine = this.tip;

            let data = "<vakcinaKolicina>" + xmljs.json2xml(this.vakcinaKolicina, {compact: true, spaces: 4}) + "</vakcinaKolicina>";
            VakcinaService.addKolicina(data)
                .then(response => console.log(response))
                .catch(console.log(greska))

        }
    },
};
</script>

<style scoped>

.container {
    margin-top: 2%;
}

#addAmountModal label
{
    display: block;
}

#addAmountModal label span
{
    display: inline-block;
    text-align: left;
    width: 200px;
}

</style>
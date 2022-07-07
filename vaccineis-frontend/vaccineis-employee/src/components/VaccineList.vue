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
                        <input disabled v-model="naziv" id="vaccine" type="text">
                    </label>
                    <label for="currentAmount">
                        <span>Тренутна количина:</span>
                        <input disabled v-model="kolicina" id="currentAmount" type="number" min="0">
                    </label>
                    <label for="amount">
                        <span>Количина за додавање:</span>
                        <input v-model="vakcina.kolicina" id="amount" type="number" min="0">
                    </label>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Затвори</button>
                <button @click="confirm" type="button" class="btn btn-primary" data-dismiss="modal">Потврди</button>
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
                            <tr v-for="vakcina in vakcine" :key="vakcina.id">
                                <td><span>{{ vakcina.naziv }}</span></td>
                                <td><span>{{ vakcina.kolicina }}</span></td>
                                <td><button @click="addAmount(vakcina)" type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAmountModal">Додај количину</button></td>
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

export default {
    name: "VaccineList",

    data() {
        return {
            vakcine: [],

            naziv: "",
            kolicina: "",
            
            vakcina: {
                id: "",
                naziv: "",
                kolicina: 0,
            }
        };
    },

    created() {
        this.loadVakcine();
    },

    methods: {

        loadVakcine() {
            VakcinaService.loadVakcine()
                .then(response => {
                    console.log("data: ", response.data);
                    this.vakcine = response.data.vakcine;
                })
                .catch((error) => console.log(error.message))
        },

        addAmount(vakcina) {
            this.vakcina.id = vakcina.id;
            this.naziv = vakcina.naziv;
            this.kolicina = vakcina.kolicina;
        },

        confirm() {
            this.vakcina.naziv = this.naziv;
            // let data = "<vakcinaKolicina>" + xmljs.json2xml(this.vakcina, {compact: true, spaces: 4}) + "</vakcinaKolicina>";
            console.log(this.vakcina);
            VakcinaService.addKolicina(this.vakcina)
                .then(() => {
                    this.toast("Успешно сте ажурирали количину вакцина!", "success");
                    this.loadVakcine();
                })
                .catch((error) => console.log(error.message))
        },

        toast(message, type) {
            this.$toasted.show(message, {
                type: type,
                theme: "toasted-primary",
                position: "top-center",
                duration: 3000,
            });
        },
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
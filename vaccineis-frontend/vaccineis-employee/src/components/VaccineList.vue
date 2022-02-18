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
                        <input disabled v-model="vaccineType" id="vaccine" type="text">
                    </label>
                    <label for="currentAmount">
                        <span>Тренутна количина:</span>
                        <input disabled v-model="currentAmount" id="currentAmount" type="number" min="0">
                    </label>
                    <label for="amount">
                        <span>Количина за додавање:</span>
                        <input v-model="amount" id="amount" type="number" min="0">
                    </label>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Затвори</button>
                <button  type="button" class="btn btn-primary">Потврди</button>
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

            vaccineType: "",
            currentAmount: 0, 

            amount: 0,
        };
    },

    methods: {

        addAmount(vaccine) {
            this.vaccineType = vaccine.tip;
            this.currentAmount = vaccine.kolicina;
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
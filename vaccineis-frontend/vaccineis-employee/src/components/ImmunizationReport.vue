<template>
    <b-container>
        <div class="row1" style="padding-bottom: 50px">
            <div class="column1">
                <div>
                    <label>Одаберите почетни датум</label>
                    <b-form-datepicker v-bind="labels.sr" :locale="'sr'" v-model="fromDate" class="mb-2"></b-form-datepicker>
                </div>
            </div>
            <div class="column1">
                <div>
                    <label>Одаберите крајњи датум</label>
                    <b-form-datepicker v-bind="labels.sr" :locale="'sr'" v-model="toDate" class="mb-2"></b-form-datepicker>
                </div>
            </div>
        </div>
        <div>
            <b-button block variant="primary" v-on:click="generisiIzvjestaj()">Генериши извештај</b-button>
        </div>
        <pdf v-if="showPdf" :src="pdfsrc" ></pdf>

    </b-container>
</template>

<script>
import IzvjestajService from "@/service/IzvjestajService";
import pdf from "vue-pdf"

export default {
    name: "ImmunizationReport",

    components: { pdf },

    data() {
        return {
            fromDate: "",
            toDate: "",
            labels: {
                sr: {
                    labelPrevDecade: 'Прошли век',
                    labelPrevYear: 'Прошла година',
                    labelPrevMonth: 'Прошли месец',
                    labelCurrentMonth: 'Тренутни месец',
                    labelNextMonth: 'Следећи месец',
                    labelNextYear: 'Следећа година',
                    labelNextDecade: 'Следећа деценија',
                    labelToday: 'Данас',
                    labelSelected: 'Одабрани датум',
                    labelNoDateSelected: 'Није одабран датум',
                    labelCalendar: 'Календар',
                    labelNav: 'Календар навигација',
                    labelHelp: 'Користите стрелице за навигацију кроз календар'
                }
            },
            pdfsrc: null,
            showPdf: true
        };
    },

    created() {
      this.getIzvjestajPdf();
    },

    methods: {
        getIzvjestajPdf() {
            IzvjestajService.viewIzvjestajPdf().then((response) => {
                this.pdfsrc = URL.createObjectURL(response.data);
                this.showPdf = true;
            });
        },
        generisiIzvjestaj() {
            console.log(this.fromDate);
            console.log(this.toDate);
            // IzvjestajService.generisiNoviIzvjestaj(this.fromDate, this.toDate);
        }
    },
};
</script>

<style scoped>

.row1 {
    display: flex;
}

.column1 {
    flex: 50%;
    padding: 10px;
    height: 50px;
}

</style>
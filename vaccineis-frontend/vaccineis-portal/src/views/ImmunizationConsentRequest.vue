<template>
<div>
    <Navbar />
    <div v-if="termin === undefined" class="alert"
         style="padding: 20px; background-color: #f44336; color: white; font-size: 20px; text-align: center; margin-top: 30px; width: 50%; margin-left: 25%;">
      
    </div>
    <div v-else-if="termin === null" class="alert"
         style="padding: 20px; background-color: #f44336; color: white; font-size: 20px; text-align: center; margin-top: 30px; width: 50%; margin-left: 25%;">
      Немате заказан термин
    </div>
    <div v-if="termin" class="alert"
         style="padding: 20px; background-color: #007bff; color: white; font-size: 20px; text-align: center; margin-top: 30px; width: 50%; margin-left: 25%;">
      Датум термина: {{fromatDate(termin)}} | време термина: {{fromatTime(termin)}}
    </div>
    <div class="modal-dialog" v-if="termin">
        <div class="modal-content">
            <ImmunizationConsentForm />
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import ImmunizationConsentForm from "@/components/ImmunizationConsentForm.vue";
import TerminService from "@/service/TerminService.js";
import xmljs from "xml-js";

export default {
    name: "ImmunizationConsentRequest",
    components: { Navbar, ImmunizationConsentForm },
    data() {
      return {
        haveAppointment: true,
        appointmentDateTime: Date.parse("2022-02-24T13:45:00"),

        termin: undefined,

      }
    },

    created() {
        this.getTermin();
    },

    methods: {
        getTermin() {
            TerminService.getNepotvrdjeniTermin()
                .then(response => { 
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4})); 
                    
                    console.log(data);
                    if (data?.['ns2:termin']?.['ns2:datumVrijeme']?.['_text']) {
                        this.termin = data['ns2:termin']['ns2:datumVrijeme']['_text'];
                    } else {
                        this.termin = null;
                    }
                })
                .catch((error) => { console.log(error) })
        },

        fromatDate(string) {
            let dateObj = new Date(string);
            return dateObj.toLocaleDateString('sr-RS');
        },

        fromatTime(string) {
            let dateObj = new Date(string);
            return dateObj.toLocaleTimeString('sr-RS').slice(0,5);
        },

    }
};
</script>

<style scoped>
.modal-dialog {
    max-width: 800px;
}
form {
    margin: 20px;
}
input {
    outline: none;
    border-radius: 5px;
    border: none;
    background: #d3d3d3;
    font-family: Tahoma;
}
label {
    font-family: Tahoma;
}
.button.primary {
    background: 0 0;
    color: #253965;
    border: none;
    text-transform: none;
    font-size: 36px;
    font-weight: 700;
    cursor: inherit;
}

</style>
<template>
<div>
    <Navbar />
    <div v-if="!haveAppointment" class="alert"
         style="padding: 20px; background-color: #f44336; color: white; font-size: 20px; text-align: center; margin-top: 30px; width: 50%; margin-left: 25%;">
      Немате заказан термин
    </div>
    <div v-if="haveAppointment" class="alert"
         style="padding: 20px; background-color: #007bff; color: white; font-size: 20px; text-align: center; margin-top: 30px; width: 50%; margin-left: 25%;">
      Датум термина: 2.24.2022 , време термина: 13:45
    </div>
    <div class="modal-dialog" v-if="haveAppointment">
        <div class="modal-content">
            <ImmunizationConsentForm />
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import ImmunizationConsentForm from "@/components/ImmunizationConsentForm.vue";

export default {
    name: "ImmunizationConsentRequest",
    components: { Navbar, ImmunizationConsentForm },
    data() {
      return {
        haveAppointment: true,
        appointmentDateTime: Date.parse("2022-02-24T13:45:00")
      }
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
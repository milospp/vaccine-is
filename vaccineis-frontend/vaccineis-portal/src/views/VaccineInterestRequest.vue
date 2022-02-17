<template>
<div>
    <Navbar />
    <div class="modal-dialog">
        <div>
            <a href="#" class="button primary">Исказивање интересовања за вакцинисање против COVID-19</a>
        </div> 
        <div class="modal-content">
            <VaccineInterestForm />
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import VaccineInterestForm from "@/components/VaccineInterestForm.vue";

export default {
    name: "VaccineInterestRequest",
    components: { Navbar, VaccineInterestForm },
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
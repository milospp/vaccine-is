<template>
<div>
    <Navbar />
    <div class="modal-dialog">
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
<template>
<div>
    <Navbar />
    <div class="modal-dialog">
        <div class="modal-content">
            <ImmunizationConsentFormPart2 :id="id"/>
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import ImmunizationConsentFormPart2 from "@/components/ImmunizationConsentFormPart2.vue";

export default {
    name: "ImmunizationConsentRequestPart2",
    components: { Navbar, ImmunizationConsentFormPart2 },
    props: {
        id: String
    },
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
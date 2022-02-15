<template>
<div>
    <Navbar />
    <div class="modal-dialog">
        <div class="modal-content">
            <ImmunizationReport />
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import ImmunizationReport from "@/components/ImmunizationReport.vue";

export default {
    name: "ImmunizationReportRequest",
    components: { Navbar, ImmunizationReport },
};
</script>

<style scoped>

.modal-dialog {
    max-width: 980px;
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


</style>
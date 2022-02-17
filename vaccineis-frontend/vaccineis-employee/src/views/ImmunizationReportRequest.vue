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

</style>
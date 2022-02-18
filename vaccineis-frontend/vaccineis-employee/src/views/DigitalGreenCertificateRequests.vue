<template>
<div>
    <Navbar />
    <div class="modal-dialog">
        <div class="modal-content">
            <DigitalGreenCertificateRequestsTable />
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import DigitalGreenCertificateRequestsTable from "@/components/DigitalGreenCertificateRequestsTable.vue";

export default {
    name: "DigitalGreenCertificateRequests",
    components: { Navbar, DigitalGreenCertificateRequestsTable },
};
</script>

<style scoped>


</style>
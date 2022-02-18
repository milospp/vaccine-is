<template>
<div>
    <Navbar />
    <div class="container">
        <DigitalGreenCertificateRequestsTable />
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
<template>
    <div>
        <Navbar />
        <ImmunizationConsentList />
    </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import ImmunizationConsentList from "@/components/ImmunizationConsentList.vue";

export default {
    name: "ImmunizationConsentRecords",
    components: { Navbar, ImmunizationConsentList },
};
</script>

<style scoped>


</style>
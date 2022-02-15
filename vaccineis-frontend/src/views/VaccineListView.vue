<template>
<div>
    <Navbar />
    <VaccineList />
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import VaccineList from "@/components/VaccineList.vue";

export default {
  name: "VaccineListView",
  components: { Navbar, VaccineList },
};
</script>
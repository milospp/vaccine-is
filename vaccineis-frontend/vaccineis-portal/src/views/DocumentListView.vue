<template>
<div>
    <Navbar />
    <DocumentList />
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import DocumentList from "@/components/DocumentList.vue";

export default {
  name: "DocumentListView",
  components: { Navbar, DocumentList },
};
</script>
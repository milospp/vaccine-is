<template>
<div>
    <Navbar />
    <div class="modal-dialog">
        <div class="modal-content">
            <RegistrationForm />
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import RegistrationForm from "@/components/RegistrationForm.vue";

export default {
    name: "Registration",
    components: { Navbar, RegistrationForm },
};
</script>
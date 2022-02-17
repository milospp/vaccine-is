<template>
<div>
    <Navbar />
    <div class="modal-dialog">
        <div class="modal-content">
            <LoginForm />
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import LoginForm from "@/components/LoginForm.vue";

export default {
  name: "Login",
  components: { Navbar, LoginForm },
};
</script>
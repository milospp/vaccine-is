<template>
<form @submit.prevent="login">
    <div class="modal-body text-start">
        <div class="form-group">
            <input v-model="authenticationData.username" type="text" class="form-control" placeholder="Korisnicko ime"/>
            <div class="invalid-feedback">Invalid username.</div>
        </div>
        <div class="form-group">
            <input v-model="authenticationData.password" type="password" class="form-control" placeholder="Lozinka"/>
            <div class="invalid-feedback">Invalid password.</div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Prijavi se</button>
    </div>
</form>
</template>

<script>
export default {
    name: "LoginForm",

    data() {
        return {
            authenticationData: {
                username: "",
                password: "",
            },
        };
    },

    methods: {
        login() {
            if (this.authenticationData.username === "" || this.authenticationData.password === "")
                return;
        },

        toast(message, type) {
            this.$toasted.show(message, {
                type: type,
                theme: "toasted-primary",
                position: "top-center",
                duration: 3000,
            });
        },
    },
};
</script>

<style scoped>
form {
  margin-top: 20px;
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
.btn {
  margin: auto;
  display: block;
}
</style>
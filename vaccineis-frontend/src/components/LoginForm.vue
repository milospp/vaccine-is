<template>
<form @submit.prevent="login">
    <div class="modal-body text-start">
        <div class="form-group">
            <input v-model="authenticationData.email" type="text" class="form-control" placeholder="Имејл"/>
            <div class="invalid-feedback">Невалидан имејл.</div>
        </div>
        <div class="form-group">
            <input v-model="authenticationData.password" type="password" class="form-control" placeholder="Лозинка"/>
            <div class="invalid-feedback">Invalid password.</div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Пријави се</button>
    </div>
</form>
</template>

<script>
export default {
    name: "LoginForm",

    data() {
        return {
            authenticationData: {
                email: "",
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
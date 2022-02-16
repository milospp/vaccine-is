<template>
<form @submit.prevent="registration">
    <div class="modal-body text-start">
        <div class="form-group">
            <input v-model="user.firstName" type="text" class="form-control" placeholder="Име"/>
            <div class="invalid-feedback">Невалидно име.</div>
        </div>
        <div class="form-group">
            <input v-model="user.lastName" type="text" class="form-control" placeholder="Презиме"/>
            <div class="invalid-feedback">Невалидно презиме.</div>
        </div>
        <div class="form-group">
            <input v-model="user.email" type="text" class="form-control" placeholder="Имејл"/>
            <div class="invalid-feedback">Невалидан имејл.</div>
        </div>
        <div class="form-group">
            <input v-model="user.password" type="text" class="form-control" placeholder="Лозинка"/>
            <div class="invalid-feedback">Невалидна лозинка.</div>
        </div>
    </div>

    <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Региструј се</button>
    </div>
</form>
</template>

<script>
export default {
    name: "RegistrationForm",

    data() {
        return {
        user: {
            email: "",
            password: "",
            firstName: "",
            lastName: "",
            jmbg: ""
        },
        };
    },

    methods: {
        registration() {

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
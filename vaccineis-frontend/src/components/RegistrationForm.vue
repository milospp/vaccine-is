<template>
<form @submit.prevent="registration">
    <div class="modal-body text-start">
        <div class="form-group">
            <input v-model="user.username" type="text" class="form-control" placeholder="Korisničko ime"/>
            <div class="invalid-feedback">Invalid username.</div>
        </div>
        <div class="form-group">
            <input v-model="user.firstName" type="text" class="form-control" placeholder="Ime"/>
            <div class="invalid-feedback">Invalid first name.</div>
        </div>
        <div class="form-group">
            <input v-model="user.lastName" type="text" class="form-control" placeholder="Prezime"/>
            <div class="invalid-feedback">Invalid last name.</div>
        </div>
        <div class="form-group">
            <input v-model="user.jmbg" type="text" class="form-control" placeholder="Jmbg"/>
            <div class="invalid-feedback">Invalid jmbg.</div>
        </div>
    </div>

    <div class="modal-footer">
        <button type="submit" class="btn btn-primary">Prijavi se</button>
    </div>
</form>
</template>

<script>
export default {
    name: "Login",

    data() {
        return {
        user: {
            username: "",
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
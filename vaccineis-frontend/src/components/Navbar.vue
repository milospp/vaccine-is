<template>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <img alt="Logo" src="../assets/images/pharmacy.png" />
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <router-link to="/" class="nav-link">Home</router-link>
            </li>
        </ul>
        <form class="d-flex">
            <router-link v-if="$store.user === undefined" to="/login" class="nav-link">Login</router-link>
            <router-link v-if="$store.user === undefined" to="/registration" class="btn btn-outline-primary">Register</router-link>
            <button v-else to="/logout" @click="logout" class="btn btn-outline-primary">Logout</button>
        </form>
        </div>
    </div>
</nav>
</template>

<script>
export default {
    name: "Navbar",

    methods: {
        logout() {
            localStorage.removeItem("token");
            this.$router.go();
        },
    },
};
</script>

<style scoped>
nav {
    z-index: 1;
    padding: 10px;
    background-color: white;
}
.profile {
    color: rgba(0, 0, 0, 0.55);
}
nav a {
    font-size: 120%;
}
.nav-item {
    font-weight: bold;
}
.navbar-brand img {
    height: 60px;
    float: left;
    margin-right: 30px;
    margin-left: 30px;
}
</style>
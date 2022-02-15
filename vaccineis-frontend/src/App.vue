<template>
    <router-view />
</template>

<script>
import axios from "axios"

export default {
    beforeMount() {
        if (!localStorage.getItem('token'))
            return;
        
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        axios.defaults.headers.common['Authorization'] = "Bearer " + token;
        axios.interceptors.response.use(function (response) {
            return response;
        }, function (error) {
            if (error.response.status === 401) {
            this.$router.push({name: "Login"});
            }
            return Promise.reject(error);
        });
    },

    created() {
        this.$store.dispatch('loadAuthorizedUser');
    },
}
</script>
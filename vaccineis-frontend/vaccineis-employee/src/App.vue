<template>
  <router-view/>
</template>

<script>
import axios from "axios"


export default {
    beforeCreate() {
        if (!localStorage.getItem('token'))
            return;
        
        let token = localStorage.getItem('token');
        axios.defaults.headers.common['Authorization'] = "Bearer " + token;
        axios.interceptors.response.use(function (response) {
            return response;
        }, function (error) {
            if (error.response.status === 401) {
                console.log("usao");
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

<style>
@import "../node_modules/@syncfusion/ej2-base/styles/material.css";
@import "../node_modules/@syncfusion/ej2-buttons/styles/material.css";
@import "../node_modules/@syncfusion/ej2-splitbuttons/styles/material.css";
@import "../node_modules/@syncfusion/ej2-dropdowns/styles/material.css";
@import "../node_modules/@syncfusion/ej2-inputs/styles/material.css";
@import "../node_modules/@syncfusion/ej2-lists/styles/material.css";
@import "../node_modules/@syncfusion/ej2-popups/styles/material.css";
@import "../node_modules/@syncfusion/ej2-calendars/styles/material.css";
@import "../node_modules/@syncfusion/ej2-vue-querybuilder/styles/material.css";
</style>
<template>
    
<table class="table">
    <thead>
        <tr>
            <th scope="col">Датум</th>
            <th scope="col">Име подносиоца</th>
            <th scope="col">Презиме подносиоца</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="zahtev in zahtevi" :key="zahtev.datum">
            <td>{{ zahtev.datum }}</td>
            <td>{{ zahtev.imePodnosioca }}</td>
            <td>{{ zahtev.prezimePodnosioca }}</td>
            <td><button class="btn btn-primary">Прегледај захтев</button></td>
            <td><button class="btn btn-primary">Прегледај потврду</button></td>
        </tr>
    </tbody>
</table>

</template>

<script>
export default {
    name: "DigitalGreenCertificateRequestsTable",

    data() {
        return {
            zahtevi: [
                {
                    datum: "01.08.2021",
                    imePodnosioca: "Marko",
                    prezimePodnosioca: "Markovic"
                }
            ]

        };
    },

    methods: {

    },
};
</script>

<style scoped>


</style>
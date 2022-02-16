<template>

<form id="vaccine-interest-form">
    <br><br><br>
    <div id="title">
        <h5><b>Извештај о имунизацији / Immunization Report</b></h5>
    </div>
    <br><br>
    <p>
        Извештај се односи на период од <strong>{{startDate}}</strong> до <strong>{{endDate}}</strong>
    </p>
    <br><br>
    <p>
        У напоменутом временском периоду је: 
        <ul>
            <li>
                поднето <strong>{{interestDocumentsNumber}}</strong> докумената о интересовању за имунизацију; 
            </li>
            <li>
                примљено <strong>{{digitalGreenSertificateRequest}}</strong> захтева за дигитални зелени сертификат, од којих је <strong>{{digitalGreenSertificateRequestsIssued}}</strong> издато;
            </li>
        </ul>
    </p>
    <br><br>
    <p>
        Дато је <strong>{{vaccinesNumber}}</strong> доза вакцине против COVID-19 вируса у следећој количини:
        <br><br>
        <table border="1" width="700">
            <thead>
                <tr>
                    <th>Редни број дозе</th>
                    <th>Број датих доза</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><b>1</b></td>
                    <td>{{vaccine1}}</td>
                </tr>
                <tr>
                    <td><b>2</b></td>
                    <td>{{vaccine2}}</td>
                </tr>
                <tr>
                    <td><b>3</b></td>
                    <td>{{vaccine3}}</td>
                </tr>
            </tbody>
        </table>
    </p>
    <br><br><br>
    <p>
        Расподела по произвођачима је: 
        <ul>
            <li>
                <strong>Pfizer, BioNTech - {{pfizer}}</strong> доза;
            </li>
            <li>
                <strong>Sinopharm - {{sinopharm}}</strong> доза;
            </li>
            <li>
                <strong>Sputnik V - {{sputnik}}</strong> доза;
            </li>
            <li>
                <strong>AstraZeneca, Oxford - {{oxford}}</strong> доза.
            </li>
        </ul>
    </p>

    <br><br><br><br><br>

    <p>
        Датум издавања: <strong style="text-decoration: underline;">11.09.2022.</strong> године
    </p>

    <br><br><br><br><br><br><br>

</form>
</template>

<script>
export default {
    name: "ImmunizationReport",

    data() {
        return {

            startDate: "01.08.2021",
            endDate: "01.09.2021",
            interestDocumentsNumber: 2758,
            digitalGreenSertificateRequest: 453,
            digitalGreenSertificateRequestsIssued: 428,
            vaccinesNumber: 3225,
            vaccine1: 1789,
            vaccine2: 1276,
            vaccine3: 160,
            pfizer: 1625,
            sinopharm: 1100,
            sputnik: 356,
            oxford: 144,
        };
    },

    methods: {

    },
};
</script>

<style scoped>

#title {
    text-align: center;
    font-weight: 50;
    color: black;
}

p {
    text-align: left;
    color: black;
    padding-left: 60px;
}

th {
    text-align: center;
}

td {
    text-align: center;
}

</style>
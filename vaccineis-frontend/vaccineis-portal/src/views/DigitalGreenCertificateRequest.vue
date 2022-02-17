<template>
<div>
    <Navbar />
    <div class="modal-dialog">
        <div id="title">
            <a href="#" class="button primary">Захтев за издавање дигиталног зеленог сертификата</a>
        </div> 
        <div class="disclaimer" style="background:#F7C3B8">
            <p class="margin-none">
                У складу са одредбом Републике Србије о издавању дигиталног зеленог сертификата као потврде о извршеној вакцинацији против COVID-19, 
                <br>резултатима тестирања на заразну болест SARS-CoV-2 или опоравку од болести COVID-19, подносим захтев за издавање дигиталног зеленог сертификата.                
                <br>Након што попуните поља кликните на тастер Поднеси захтев и Дигитални зелени сертификат ће Вам бити послат на имејл адресу коју сте навели.
            </p>
        </div>
        <div class="modal-content">
            <DigitalGreenCertificateForm />
        </div>
    </div>
</div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import DigitalGreenCertificateForm from "@/components/DigitalGreenCertificateForm.vue";

export default {
    name: "DigitalGreenCertificateRequest",
    components: { Navbar, DigitalGreenCertificateForm },
};
</script>

<style scoped>
#title {
    text-align: center;
}

.disclaimer {
    border-radius: 3px;
}

.margin-none {
    padding: 10px;
}

.modal-dialog {
    max-width: 980px;
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
.button.primary {
    background: 0 0;
    color: #253965;
    border: none;
    text-transform: none;
    font-size: 36px;
    font-weight: 700;
    cursor: inherit;
}

</style>
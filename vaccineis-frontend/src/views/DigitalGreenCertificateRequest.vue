<template>
<div>
    <Navbar />
    <div class="modal-dialog">
        <div id="title">
            <a href="#" class="button primary">Дигитални зелени сертификат / Digital green certificate</a>
        </div> 
        <div class="disclaimer" style="background:#F7C3B8">
            <p class="margin-none">
                Уколико планирате да Дигитални зелени сертификат користите за путовања ван граница Републике Србије, потребно је да унесете број важећег пасоша.
                <br>Након што попуните поља кликните на тастер Поднеси захтев и Дигитални зелени сертификат ће Вам бити послат на имејл адресу коју сте навели.
                <br>Обавезна поља су означена звездицом (*).
                <br>
                <br>
                If you plan to use the Digital Green Certificate for travel outside the borders of the Republic of Serbia, you need to enter the valid passport number.
                <br>After filling in the fields, click on the Apply button and the Digital Green Certificate will be sent to the email address you provided.
                <br>Required fields are marked with an asterisk (*).
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
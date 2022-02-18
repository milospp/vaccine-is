<template>
    <div>
        <Navbar />
        <article>
            <h1 class="button-primary" >Добродошли службеници</h1>
            <h2>Изаберите једну од могућности из навигационог бара</h2>
            <img alt="Vue logo" src="@/assets/images/pharmacy.png">

        </article>
    </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
    name: "Home",
    components: { Navbar },
};
</script>

<style scoped>
article img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 40%;  
}
article h1 {
  margin-top: 3%;
  margin-bottom: 2%;
  text-align: center;
  font-weight: bold;
  font-size: 300%;
}
article h2 {
    text-align: center;
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
import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
{
    path: "/",
    name: "Home",
    component: Home,
},
{
    path: encodeURI("/пријава"),
    name: "Login",
    component: () => import("@/views/Login.vue"),
},
{
    path: encodeURI("/регистрација"),
    name: "Registration",
    component: () => import("@/views/Registration.vue"),
},
{
    path: encodeURI("/исказивање-интересовања"),
    name: "VaccineInterestRequest",
    component: () => import("@/views/VaccineInterestRequest.vue"),
},
{
    path: encodeURI("/зелени-сертификат-захтев"),
    name: "DigitalGreenCertificateRequest",
    component: () => import("@/views/DigitalGreenCertificateRequest.vue"),
}
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router

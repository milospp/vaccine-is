import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
{
    path: encodeURI("/"),
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
},
{
    path: encodeURI("/преглед-докумената"),
    name: "DocumentListView",
    component: () => import("@/views/DocumentListView.vue"),
},
{
    path: encodeURI("/вакцине"),
    name: "VaccineListView",
    component: () => import("@/views/VaccineListView.vue"),
},
{
    path: encodeURI("/извештај-о-имунизацији"),
    name: "ImmunizationReportRequest",
    component: () => import("@/views/ImmunizationReportRequest.vue"),
},
{
    path: encodeURI("/образац-сагласности-за-имунизацију"),
    name: "ImmunizationConsentRequest",
    component: () => import("@/views/ImmunizationConsentRequest.vue"),
},
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
{
    path: encodeURI('/'),
    name: 'Home',
    component: Home
},
{
    path: encodeURI("/пријава"),
    name: "Login",
    component: () => import("@/views/Login.vue"),
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
    path: encodeURI("/претрага-докумената"),
    name: "DocumentsSearch",
    component: () => import("@/views/SearchDocuments")
},
{
    path: encodeURI("/dzs-zahtevi"),
    name: "DigitalGreenCertificateRequests",
    component: () => import("@/views/DigitalGreenCertificateRequests.vue"),
},
{
    path: encodeURI("/преглед-докумената/пацијент/:uuid"),
    name: "DocumentsListForPacient",
    component: () => import("@/views/DocumentListView.vue"),
},
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

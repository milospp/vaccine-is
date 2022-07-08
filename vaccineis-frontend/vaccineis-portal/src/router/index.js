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
    meta: {
        authorize: ['GRADJANIN']
    }
},
{
    path: encodeURI("/зелени-сертификат-захтев"),
    name: "DigitalGreenCertificateRequest",
    component: () => import("@/views/DigitalGreenCertificateRequest.vue"),
    meta: {
        authorize: ['GRADJANIN']
    }
},
{
    path: encodeURI("/преглед-докумената"),
    name: "DocumentListView",
    component: () => import("@/views/DocumentListView.vue"),
    meta: {
        authorize: ['GRADJANIN']
    }
},
{
    path: encodeURI("/образац-сагласности-за-имунизацију"),
    name: "ImmunizationConsentRequest",
    component: () => import("@/views/ImmunizationConsentRequest.vue"),
    meta: {
        authorize: ['GRADJANIN']
    }
},
{
    path: encodeURI("/образац-сагласности-за-имунизацију-одговор"),
    name: "ImmunizationConsentRequestPart2",
    component: () => import("@/views/ImmunizationConsentRequestPart2.vue"),
    props: true,
    meta: {
        authorize: ['ZDRAVSTVENI_RADNIK']
    }
},
{
    path: encodeURI("/образци-сагласности-за-имунизацију"),
    name: "ImmunizationConsentRecords",
    component: () => import("@/views/ImmunizationConsentRecords.vue"),
    meta: {
        authorize: ['ZDRAVSTVENI_RADNIK']
    }
},
{
    path: encodeURI("/потврда/645155822"),
    name: "ViewGreenCertificate",
    component: () => import("@/components/ViewDocument.vue"),
},
{
    path: encodeURI("/дигитални/645155822"),
    name: "ViewGreenCertificate",
    component: () => import("@/components/ViewDocument.vue"),
}
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

router.beforeEach((to, from, next) => {
    const { authorize } = to.meta;
    const currentUser = JSON.parse(localStorage.getItem('user'));

    if (authorize) {

        if (!currentUser) {
            // not logged in so redirect to login page with the return url
            return next({ name: 'Login' });
        }

        // check if route is restricted by role
        if (authorize.length && !authorize.includes(currentUser.rola)) {
            // role not authorised so redirect to home page
            return next({ path: '/' });
        }
    }

    next();

});

export default router

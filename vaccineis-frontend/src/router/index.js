import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
{
    path: "/",
    name: "Почетна",
    component: Home,
},
{
    path: "/пријава",
    name: "Login",
    component: () => import("@/views/Login.vue"),
},
{
    path: "/регистрација",
    name: "Registration",
    component: () => import("@/views/Registration.vue"),
},
{
    path: "/исказивање-интересовања",
    name: "VaccineInterestRequest",
    component: () => import("@/views/VaccineInterestRequest.vue"),
}
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router

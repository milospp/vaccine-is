import Vue from "vue";
import Vuex from "vuex";
import AuthenticationService from "@/service/AuthenticationService.js";
import xmljs from "xml-js";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        user: {
            rola: undefined //"GRADJANIN" // "SLUZBENIK", "ZDRAVSTVENI_RADNIK"
        },
    },

    getters: {
        rola: state => { 
            return (state.user.rola) ? state.user.rola : undefined; 
        }
    },

    mutations: {
        SET_AUTHORIZED_USER (state, user) {
            state.user.ime = user.ime["_text"];
            state.user.prezime = user.prezime["_text"];
            state.user.jmbg = user.jmbg["_text"];
            state.user.email = user.email["_text"];
            state.user.rola = user.rola["_text"];
        },
    },

    actions: {
        loadAuthorizedUser( {commit} ) {
            if (!localStorage.getItem('token'))
                return;

            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            AuthenticationService.getAuthorizedUser(token)
                .then(response => { 
                    let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                    let user = data["AuthenticationResponseDTO"];
                    commit('SET_AUTHORIZED_USER', user); 
                })
                .catch(error => { console.log(error); });
        }
    },

    modules: {},
});

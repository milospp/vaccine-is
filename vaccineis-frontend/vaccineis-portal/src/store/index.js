import Vue from "vue";
import Vuex from "vuex";
import AuthenticationService from "@/service/AuthenticationService.js";
import xmljs from "xml-js";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        user: {
            ime: undefined,
            prezime: undefined,
            email: undefined,
            rola: undefined
        },
        
        // eslint-disable-next-line
        emailRegex: /[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})/,

        // eslint-disable-next-line
        jmbgRegex: /(0[1-9]|[1-2][0-9]|31(^(0[2469]|11)?)|30(^02))(0[1-9]|1[0-2])([09][0-9]{2})([0-8][0-9]|9[0-6])([0-9]{3})(\d)/,
        brojFiksnogRegex: /0[1-3][0-9](0)?[0-9]{6,7}/,
        brojMobilnogRegex: /06[0-9]{7,8}/,
    },

    getters: {
        rola: state => { 
            return (state.user.rola) ? state.user.rola : undefined; 
        },
        user: state => {
            return state.user;
        }
    },

    mutations: {
        SET_AUTHORIZED_USER (state, user) {
            state.user.ime = user.ime["_text"];
            state.user.prezime = user.prezime["_text"];
            state.user.email = user.email["_text"];
            state.user.rola = user.rola["_text"];
        },
    },

    actions: {
        loadAuthorizedUser( {commit} ) {
            if (!localStorage.getItem('token'))
                return;

            let token = localStorage.getItem('token');

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

import Vue from 'vue'
import Vuex from 'vuex'
import xmljs from 'xml-js';
import AuthenticationService from '@/service/AuthenticationService.js';

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: {
            rola: undefined
        },

        naprednaList: []
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
            state.user.email = user.email["_text"];
            state.user.rola = user.rola["_text"];
        },

        SET_NAPREDNA_LIST (state, list) {
            console.log(list);
            state.naprednaList = list;
        }
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
        },

        setNaprednaList({commit}, data) {
            console.log(data);
            commit('SET_NAPREDNA_LIST', data);
        }
    },

    modules: {},
})

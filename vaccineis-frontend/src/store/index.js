import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        user: {
            role: undefined
        },
    },

    mutations: {
        SET_AUTHORIZED_USER (state, user) {
            state.user = user;
        },
    },

    actions: {
        loadAuthorizedUser( {commit} ) {
            if (!localStorage.getItem('token'))
                return;

            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            console.log(token);

            commit('SET_AUTHORIZED_USER', ""); 
            // AuthenticationService.getAuthorizedUser(token)
            //     .then(response => { commit('SET_AUTHORIZED_USER', response.data); })
            //     .catch(error => { console.log(error); });
        }
    },

    modules: {},
});

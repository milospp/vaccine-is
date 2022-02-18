import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Toasted from 'vue-toasted'
import { QueryBuilderPlugin } from '@syncfusion/ej2-vue-querybuilder'
import { L10n, setCulture } from '@syncfusion/ej2-base';
import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import 'bootstrap/dist/css/bootstrap.css';

Vue.use(BootstrapVue);
Vue.config.productionTip = false
Vue.use(Toasted);
Vue.use(QueryBuilderPlugin);

setCulture('en-US');

L10n.load({
  'en-US': {
    'querybuilder': {
      'AddGroup': 'Додај групу',
      'AddCondition': 'Додај услов',
      'DeleteRule': 'Обриши услов',
      'DeleteGroup': 'Обриши групу',
      'Edit': 'Измени',
      'SelectField': 'Одабери поље',
      'SelectOperator': 'Одабери оператор',
      'StartsWith': 'Почиње са',
      'EndsWith': 'Завршава се са',
      'Contains': 'Садржи',
      'Equal': 'Једнако',
      'NotEqual': 'Различито',
      'LessThan': 'Мање',
      'LessThanOrEqual': 'Мање једнако',
      'GreaterThan': 'Веће',
      'GreaterThanOrEqual': 'Веће једнако',
      'Between': 'Између',
      'NotBetween': 'Није између',
      'In': 'У',
      'AND': 'И',
      'OR': 'ИЛИ',
      'NotIn': 'Није у',
      'Remove': 'Уклони',
      'ValidationMessage': 'Валидациона порука',
      'Add Group/Condition': 'Додај групу/услов'
    }
  }
});



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

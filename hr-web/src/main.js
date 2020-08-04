import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI, { size: 'mini' });

Vue.config.productionTip = false

import { postRequest } from "./util/api";
import { putRequest } from "./util/api";
import { deleteRequest } from "./util/api";
import { getRequest } from "./util/api";


Vue.prototype.$postRequest = postRequest;
Vue.prototype.$putRequest = putRequest;
Vue.prototype.$deleteRequest = deleteRequest;
Vue.prototype.$getRequest = getRequest;
import 'font-awesome/css/font-awesome.min.css'
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

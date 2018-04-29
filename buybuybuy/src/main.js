// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import Axios from 'axios'

import InputSearch from './components/inputSearch'
import loginOrRegister from './components/loginOrRegister'

Vue.prototype.$axios = Axios
Vue.config.productionTip = false
Vue.use(ElementUI)

//
// Vue.component('InputSearch', InputSearch)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
import Vue from 'vue'
import Router from 'vue-router'

import Index from '../pages/index.vue'
import login from '../login/login'
import manage from '../manage/manage'
Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/manage',
      name: 'manage',
      component: manage
    }
  ]
})

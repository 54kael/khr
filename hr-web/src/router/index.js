import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
import { initMenu } from '../util/menus'

Vue.use(VueRouter)

const routes = [
  {
    // 路由重定向
    path:'/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: () => import('../views/Login.vue'),
    hidden: true
  },
  {
    path: '/home',
    component: () => import('../views/Home.vue'),
    hidden: true
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  console.log(to.path)
  if (to.path == '/login') {
    next();
  } else {
    // 从store获取路由表，判断路由表是否为空
    let routes = store.state.routes;
    if (routes.length == 0) {
      // 初始化路由表
      initMenu(router, store);     
      next();
    } else {
      next();
    }
  }
})


export default router

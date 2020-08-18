import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
import { formatRoutes } from '../util/menus'
import { getRequest } from '../util/api';

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
  },
  {
    path: '/chat',
    component: () => import('../views/Chat.vue')
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path == '/login') {
    next();
  } else {
    // 从store获取路由表，判断路由表是否为空
    let routes = store.state.routes;
    if (routes.length == 0) {
      // 初始化路由表
      getRequest("/menu").then(resp => {
        if (resp.code == "00000") {
            // 将数据格式化成路由数组
            let fmtRoutes = formatRoutes(resp.data.menus);
            // 添加到路由表
            router.addRoutes(fmtRoutes);
            // 保存路由表到store
            store.commit('initRoutes', fmtRoutes);
            next(to.path);   
        }
    })
    } else {
      next();
    }
  }
})


export default router

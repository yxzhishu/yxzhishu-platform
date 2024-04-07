import { RouteRecordRaw, createRouter, createWebHashHistory } from 'vue-router'
import Layout from '../layout/index.vue'

import { useRoutesStore } from '../stores/routes'

const modules = import.meta.glob(`../views/**/**.vue`)

export const constantRoutes = [
    {
        path: '/',
        name: 'layout',
        component: Layout,
        redirect: 'home',
        children: [
            {
                path: '/404',
                name: '404',
                component: () => import('../layout/error/404.vue')
            },
            {
                path: '/501',
                name: '501',
                component: () => import('../layout/error/501.vue')
            },
            {
                path: '/',
                name: 'home',
                component: () => import('../views/home/index.vue')
            }
        ]
    },
    {
        name: 'login',
        path: '/login',
        component: () => import('../views/login.vue')
    }
]

const router = createRouter({
    //history: createWebHashHistory(import.meta.env.BASE_URL),
    history: createWebHashHistory(),
    routes: constantRoutes
})

export default router

//添加动态路由
export async function initRoutes() {
    // router.go(0);
    if (useRoutesStore().routeList.length <= 0) {
        return Promise.resolve(true);
    }
    useRoutesStore().routeList.map((route: RouteRecordRaw) => {
        loadAsyncRouters(route);
        router.addRoute('layout', route);
        return true;
    })
    useRoutesStore().restoreAsyncRoute();
}
//递归处理异步路由组件
async function loadAsyncRouters(route: RouteRecordRaw) {
    route.component = modules[`../views/${route.component}`];
    route.children?.map((routeCh: RouteRecordRaw) => {
        loadAsyncRouters(routeCh);
    })
}

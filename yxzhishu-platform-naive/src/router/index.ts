import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Layout from '../layout/index.vue'

const router = createRouter({
    history: createWebHistory(""),
    routes: [
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
})

export default router

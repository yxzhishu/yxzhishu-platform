import router, { initRoutes } from './index'

import { useRoutesStore } from '../stores/routes'
import { useUserInfoStore } from '../stores/userInfo'

router.beforeEach(async (to, _from, next) => {
    try {
        const token = useUserInfoStore().user?.token

        if (!token && to.path === '/login') {
            next()
        } else {
            if (!token) {
                next('/login');
            } else if (token && to.path === '/login') {
                next('/')
            } else {
                if (useRoutesStore().lossAsyncRoute) {
                    console.log("丢失动态路由, 重新加载");
                    await initRoutes();
                    next({ path: to.path, query: to.query });
                } else {

                    console.log("进入了else2", to.path);
                    if (!router.getRoutes().filter(route =>
                        route.path == to.path).length) {
                        return next('/404');
                    }
                    else if (router.getRoutes().filter(route =>
                        route.path == to.path
                        && route.components == undefined
                        && route.meta.isFrame != "1").length) {
                        console.log("无实际组件且地址不为外链");
                        return next('/501');
                    }
                    next();
                }
            }
        }
    } catch (error) {
        console.log("beforeEach错误", error);
    }
})
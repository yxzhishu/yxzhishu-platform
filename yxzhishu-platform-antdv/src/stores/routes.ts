import { defineStore } from 'pinia'
import useSessionStorage from '../utils/useSessionStorage'


export const useRoutesStore = defineStore('routes', {
    state: () => {
        return {
            //路由列表
            routeList: useSessionStorage.getter('routeList'),
            keepAliveNames: [],
            cachedViews: [],
            //丢失动态路由
            lossAsyncRoute: true,
            //活动路由标签
            keepTags: []
        }
    },
    actions: {
        setRoutesList(data: Array<string>) {
            this.routeList = data;
            useSessionStorage.setter("routeList", data);
            this.lossAsyncRoute = false;
        },
        restoreAsyncRoute() {
            this.lossAsyncRoute = false;
        },
        removeRolte() {
            this.routeList = null,
                useSessionStorage.remove("routeList");
        }

    }
})

<template>
    <!-- collapsed折叠菜单  collapsible是否可收起 -->
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible class="layout-sider">
        <!-- 图标：用于折叠菜单 -->
        <div class="logo" @click="() => (collapsed = !collapsed)" />
        <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline" @click="handleGoRouter">
            <!-- 遍历菜单 -->
            <template v-for="menu in menus" :key="menu.path">
                <!-- 隐藏菜单不展示 -->
                <template v-if="'1' != menu.meta.hidden">
                    <a-menu-item v-if="!existChildrenMenu(menu.children)" :key="menu.path">
                        <template #icon>
                            <component v-if="menu.meta.icon" :is="menu.meta.icon"></component>
                        </template>
                        <span>{{ menu.meta.title }}</span>
                    </a-menu-item>
                    <template v-else>
                        <SubMenuVue :key="menu.path" :menu="menu" />
                    </template>
                </template>
            </template>
        </a-menu>
    </a-layout-sider>
</template>
<script setup lang='ts'>
const selectedKeys = ref<string[]>(['1']);
import { ref } from 'vue';
import { useRouter } from 'vue-router'
import { useRoutesStore } from '../../stores/routes'
import { storeToRefs } from 'pinia';

import SubMenuVue from './subMenu.vue'

const routesStore = useRoutesStore();
const { routeList } = storeToRefs(routesStore)
const menus = routeList;

const router = useRouter();

const collapsed = ref<boolean>(false);


const handleGoRouter = ({ key }: any) => {
    router.push(key)
}

const existChildrenMenu = (menu: any) => {
    if (!menu || menu.length === 0) {
        return false;
    }
    if (menu.filter((item: { meta: { hidden: boolean; }; }) => item.meta.hidden != true).length > 0) {
        return true;
    }
    return false;
}

</script>
<style scoped>
.layout-sider {
    height: 100vh;
    overflow: auto;
}

.trigger {
    font-size: 18px;
    line-height: 64px;
    padding: 0 24px;
    cursor: pointer;
    transition: color 0.3s;
}

.trigger:hover {
    color: #1890ff;
}

.site-layout .site-layout-background {
    background: #fff;
}

.logo {
    height: 32px;
    background: rgba(255, 255, 255, 0.3);
    margin: 16px;
}
</style>
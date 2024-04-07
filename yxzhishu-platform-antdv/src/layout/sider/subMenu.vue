<template>
    <a-sub-menu :key="menu.path">
        <template #icon>
            <component v-if="menu.meta.icon" :is="menu.meta.icon" />
        </template>
        <template #title>{{ menu.meta.title }}</template>
        <template v-for="item in menu.children" :key="menu.path">
            <template v-if="'1' != item.meta.hidden">
                <template v-if="!existChildrenMenu(item.children)">
                    <a-menu-item :key="item.path">
                        <!-- <template #icon>
                        <component :is="item.meta.icon" />
                    </template> -->
                        <component v-if="item.meta.icon" :is="item.meta.icon" />
                        {{ item.meta.title }}
                    </a-menu-item>
                </template>
                <template v-else>
                    <sub-menu :menu="item" :key="item.path" />
                </template>
            </template>
        </template>
    </a-sub-menu>
</template>
<script setup lang='ts'>
defineProps(['menu'])
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
<style></style>
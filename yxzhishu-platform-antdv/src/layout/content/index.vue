<template>
    <a-layout-content :style="{ margin: '12px 12px', padding: '24px', background: '#fff' }">
        <RouterView v-slot="{ Component }">
            <KeepAlive>
                <component :key="$route.name" :is="Component" v-if="$route.meta.keepAlive" />
            </KeepAlive>
            <component :key="$route.name" :is="Component" v-if="!$route.meta.keepAlive" />
        </RouterView>
    </a-layout-content>
</template>
<script setup lang='ts'>
import { useRoute } from 'vue-router';
const route = useRoute();
const $route = route;
</script>
<style></style>
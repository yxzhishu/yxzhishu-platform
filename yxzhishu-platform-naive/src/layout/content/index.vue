<template>
    <div class="content-box">
        <RouterView v-slot="{ Component }">
            <KeepAlive>
                <component :key="$route.name" :is="Component" v-if="$route.meta.keepAlive" />
            </KeepAlive>
            <component :key="$route.name" :is="Component" v-if="!$route.meta.keepAlive" />
        </RouterView>
    </div>
</template>
<script setup lang='ts'>

</script>
<style>
.content-box {
    height: 100%;
    background-color: rgb(194, 223, 213);
    overflow: auto;
}
</style>
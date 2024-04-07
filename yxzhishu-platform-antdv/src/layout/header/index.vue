<template>
    <a-layout-header class="layout-header">
        <NavbrVue />
        <TagsVue />
    </a-layout-header>
</template>
<script setup lang='ts'>
import TagsVue from './tagsBox.vue';
import NavbrVue from './navbr.vue'
</script>
<style scoped>
.layout-header {
    background: #fff;
    padding: 0;
    height: 80px;
}
</style>
<template>
    <a-layout>
        <!-- 侧边栏布局-->
        <SiderVue />
        <a-layout>
            <!-- 头部布局-->
            <HeaderVue />
            <!-- 主体布局-->
            <ContentVue class="aaaa" />
        </a-layout>
    </a-layout>
</template>
<script lang="ts" setup>
import SiderVue from './sider/index.vue'
import HeaderVue from './header/index.vue'
import ContentVue from './content/index.vue'
</script>
<style>
.ant-layout {
    overflow: auto;
    flex: auto;
}

.aaaa {
    /* 不懂：加个这个不会超出布局了 */
    height: 0px;
    overflow: auto;
}
</style>

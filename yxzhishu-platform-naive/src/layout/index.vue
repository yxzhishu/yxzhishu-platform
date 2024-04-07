<template>
    <!-- 全局主题 -->
    <n-config-provider :theme="theme">
        <n-layout style="height: 100vh;">
            <!-- 头部 -->
            <n-layout-header bordered style="height: 50px;">
                <HeaderV :theme="theme == null ? 'deep' : 'shallow'" @changeTheme="value => changeTheme(value)" />
            </n-layout-header>
            <n-layout has-sider style="height: calc(100vh - 50px);">
                <!-- 侧边栏 -->
                <n-layout-sider bordered collapse-mode="width" :collapsed-width="64" :width="240" show-trigger
                    :native-scrollbar="false">
                    <SiderV />
                </n-layout-sider>
                <n-layout>
                    <div class="main-box">
                        <!-- 内容 -->
                        <n-layout-header style="height: 24px;">
                            <TagV />
                        </n-layout-header>
                        <n-layout-content class="content" style="flex: 1;" content-style="padding: 24px;">
                            <ContentV />
                        </n-layout-content>
                    </div>
                </n-layout>
            </n-layout>
        </n-layout>
    </n-config-provider>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
//主题
import type { GlobalTheme } from 'naive-ui';
import { darkTheme } from 'naive-ui'
//子组件
import SiderV from './sider/index.vue'
import HeaderV from './header/index.vue'
import TagV from './tag/index.vue'
import ContentV from './content/index.vue'

const theme = ref<GlobalTheme | null>(null);

const changeTheme = (value: String) => {
    theme.value = (value == 'deep' ? darkTheme : null)
}

</script>
<style scoped>
.main-box {
    display: flex;
    flex-flow: column;
    height: 100%;
}

.content {}
</style>
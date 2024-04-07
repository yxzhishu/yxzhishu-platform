<template>
    <div class="tags">
        <a-tag closable @close="closeTags(index)" color="processing" v-for="(item, index) in tagsList" :key="item.path"
            v-bind:class="{ 'active-tag': item.path === route.fullPath }">
            <RouterLink :to="item.path" class="tags-li-title">
                {{ item.title }}
            </RouterLink>
        </a-tag>
    </div>
</template>
<script setup lang='ts'>
import { reactive, watch } from 'vue';
//获取路由
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

//标签列表
const tagsList: Array<any> = reactive([
    { title: '主页', path: '/home', name: 'home' }
]);
//监听路由变化
watch(() => route.path, () => {
    setTags();
})

//设置标签页
function setTags() {
    const specialRoutes = ["/404", "/501"];
    if (specialRoutes.includes(route.fullPath)) {
        return;
    }
    if (route.meta.hidden === '1') {
        return;
    }
    if (!tagsList.some(item => item.path === route.fullPath)) {
        // if (tagsList.length >= 8) {
        //     tagsList.shift();
        // }

        tagsList.push({
            title: route.meta.title,
            path: route.fullPath,
            name: route.name,
        });
    }
}

//关闭单个标签
function closeTags(index: number) {
    const delItem = tagsList.splice(index, 1)[0];
    const item = tagsList[index] ? tagsList[index] : tagsList[index - 1];
    if (item) {
        delItem.path === route.fullPath && router.push(item.path);
    } else {
        router.push("/");
    }
}

</script>
<style scoped>
.tags {
    height: 30px;
    line-height: 30px;
    overflow: auto;
    padding: 0px 10px;
}

.active-tag {
    background-color: #f3f3f3;
    font-weight: bold;
}
</style>
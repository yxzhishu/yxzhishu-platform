<template>
    <div class="error-page">
        <div class="error-code">501</div>
        <div class="error-desc">啊哦~ 此页面暂时没有实现</div>
        <div class="error-handle">
            <router-link to="/">
                <a-button type="primary" ghost>返回首页</a-button>
            </router-link>
            <a-button class="error-btn" type="primary" ghost @click="goBack">返回上一页</a-button>
        </div>
    </div>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router'
const router = useRouter();
const goBack = () => {
    router.back();
}
</script>
<style scoped>
.error-page {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 100%;
    height: 100%;
    /* height: calc(100vh - 110px); */
    /* background: #f3f3f3; */
    box-sizing: border-box;
}

.error-code {
    line-height: 1;
    font-size: 250px;
    font-weight: bolder;
    color: #2d8cf0;
}

.error-code span {
    color: #00a854;
}

.error-desc {
    font-size: 30px;
    color: #777;
}

.error-handle {
    margin-top: 30px;
    padding-bottom: 200px;
}

.error-btn {
    margin-left: 100px;
}
</style>
<template>
    <n-flex style="padding: 2px;" size="small">
        <n-tag closable @close="handleClose" size="small">
            爱在西元前
        </n-tag>
        <n-tag type="success" closable @close="handleClose" @click="handleClick" size="small">
            不该
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
        <n-tag type="info" closable @close="handleClose" size="small">
            超人不会飞
        </n-tag>
    </n-flex>
</template>
<script setup lang='ts'>

const handleClose = () => {
    console.log('tab');
}

const handleClick = () => {
    console.log('aaa');

}
</script>
<style scoped>
.n-flex {
    height: 100%;
}
</style>
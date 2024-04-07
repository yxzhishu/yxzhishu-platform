<template>
    <n-flex justify="space-between" :wrap="false">
        <div class="left-box">
            <n-gradient-text type="danger">
                yx_
            </n-gradient-text>
        </div>
        <div class="right-box">
            <n-flex justify="end" size="large">
                <div class="box-item">
                    <n-button @click="changeTheme">
                        {{ props.theme }}
                    </n-button>
                </div>
                <div class="box-item">
                    <n-avatar />
                    <n-dropdown trigger="click" :options="options" @select="handleSelect">
                        <div class="box-item">
                            <span>管理yuan</span>
                            <n-icon size="16" :component="ChevronDownOutline" />

                        </div>
                    </n-dropdown>



                </div>
            </n-flex>
        </div>
    </n-flex>
</template>
<script setup lang='ts'>
import { ChevronDownOutline } from '@vicons/ionicons5'

//给父组件传值
const emit = defineEmits(['changeTheme'])
const changeTheme = () => {
    emit('changeTheme', props.theme)
}
//接收自父组件
const props = defineProps({
    theme: {
        default: 'shallow',
        type: String
    }
})

const options = [
    {
        label: '个人信息',
        key: 'profile',
    },
    {
        label: '退出登录',
        key: "logout"
    }
]
function handleSelect(key: string | number) {
    console.log(key);

}

</script>
<style>
.n-flex {
    height: 100%;
}

.box-item {
    display: flex;
    align-items: center;
}

.left-box {
    padding-left: 20px;
}

.right-box {
    padding-right: 20px;
}

.n-avatar {
    margin-right: 5px;
}

.n-gradient-text {
    font-size: 30px;
}
</style>
<template>
    <div class="navbr">
        <div class="left-navbr">
            <h2>platform</h2>
        </div>
        <div class="right-navbr">
            <a-space>
                <a-badge :count="count" dot style=" bottom: -5px;">
                    <BellOutlined style="font-size: 30px" />
                </a-badge>
                <a-avatar size="large">
                    <template #icon>

                    </template>
                </a-avatar>
                <a-dropdown :trigger="['click']">
                    <div>
                        {{ nickName }}
                        <DownOutlined />
                    </div>

                    <template #overlay>
                        <a-menu @click="onClickMenu">
                            <a-menu-item key="userInfo">个人信息</a-menu-item>
                            <a-menu-divider />
                            <a-menu-item key="exit">退出登陆</a-menu-item>
                        </a-menu>
                    </template>
                </a-dropdown>
            </a-space>
        </div>
    </div>
</template>

<script setup lang='ts'>
import { ref } from 'vue';
import { message, type MenuProps } from 'ant-design-vue';
import { BellOutlined, DownOutlined, } from '@ant-design/icons-vue';
import { useUserInfoStore } from '../../stores/userInfo'
import { apiDoLogout } from '../../api/system/accountApi'
import router from '../../router';

const nickName = ref(useUserInfoStore().user.nickname);

const count = ref<number>(1);

const onClickMenu: MenuProps['onClick'] = async ({ key }) => {
    console.log(`Click on item ${key}`);
    if (key === 'userInfo') {
        message.info("好像没有完成哎")
    } else if (key === "exit") {
        await useUserInfoStore().cleanUser();
        apiDoLogout().then(() => {
            message.success("已退出")
            router.push("/login")
        })
    } else {
    }
};

</script>

<style>
.navbr {
    height: 50px;
    line-height: 50px;
    border-bottom: 1px solid;
    display: flex;
    justify-content: space-between;
    padding: 0px 10px;
}
</style>
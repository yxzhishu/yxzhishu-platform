<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">platform</div>
            <div class="ms-main">
                <a-form ref="formRef" :model="formState" name="basic" :rules="rules" :label-col="{ span: 4 }"
                    :wrapper-col="{ span: 20 }" autocomplete="off" @finish="onFinish" @finishFailed="onFinishFailed">
                    <a-form-item label="用户" name="accountCode">
                        <a-input v-model:value="formState.accountCode" />
                    </a-form-item>

                    <a-form-item label="密码" name="password">
                        <a-input-password v-model:value="formState.password" />
                    </a-form-item>

                    <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
                        <a-button type="primary" html-type="submit" :loading="loading">登录</a-button>
                    </a-form-item>
                </a-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { Rule } from 'ant-design-vue/es/form';
import { reactive, ref } from 'vue';

import { useUserInfoStore } from '../stores/userInfo'
import { useRoutesStore } from '../stores/routes'

import { apiDoLogin } from '../api/system/accountApi';
import { apiFindRoute } from '../api/system/menuApi'

import router, { initRoutes } from '../router';
import { message } from 'ant-design-vue';

const formRef = ref();
interface FormState {
    accountCode: string;
    password: string;
}

const formState = reactive<FormState>({
    accountCode: '',
    password: '',
});
//登陆加载中
const loading = ref(false)
const rules: Record<string, Rule[]> = {
    accountCode: [
        { required: true, message: '请输入您的账号!' },
        { min: 4, max: 30, message: '长度应为4到30', trigger: 'blur' },
    ],
    password: [
        { required: true, message: '请输入您的密码!' },
        { min: 6, message: '最小密码长度为6', trigger: 'blur' },
    ]
}


const onFinish = () => {
    formRef.value.validate().then(async () => {
        loading.value = true
        //登录
        await apiDoLogin(formState).then(async res => {
            await apiFindRoute().then(async resFindRoute => {
                await setUserStores(res.data);
                useRoutesStore().setRoutesList(resFindRoute.data);
                let isNoPower = await initRoutes();
                if (isNoPower) {
                    message.warning('抱歉，您没有登录权限');
                    sessionStorage.clear();
                } else {
                    message.success('登陆成功');
                    setTimeout(function () {
                        router.push("/")
                    }, 800)
                }
            })
        })
    }).catch((err: any) => {
        console.log(err);

        loading.value = false
    });
};

const onFinishFailed = (errorInfo: any) => {
    console.log('Failed:', errorInfo);
};
/**
 * 设置UserStores
 * @param userInfo 用户信息
 */
function setUserStores(userInfo: any) {
    let sessionValue = userInfo.accountInfo;
    sessionValue.token = userInfo.tokenInfo.tokenValue;
    useUserInfoStore().setUser(sessionValue);
}
</script>

<style>
.login-wrap {
    position: absolute;
    width: 100%;
    height: 100vh;
    background-size: 100%;
}

.ms-title {
    width: 100%;
    line-height: 70px;
    text-align: center;
    font-size: 30px;
    border-bottom: 1px solid #ddd;
}

.ms-main {
    margin-top: 20px;
}

.ms-login {
    position: absolute;
    width: 400px;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    border-radius: 5px;
    overflow: hidden;
}

.login-tips {
    font-size: 12px;
    line-height: 30px;
}
</style>

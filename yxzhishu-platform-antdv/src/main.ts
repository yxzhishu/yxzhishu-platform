import { createApp } from 'vue'
import App from './App.vue'
//路由
import router from './router'
//状态管理
import { createPinia } from 'pinia'

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
// 引入图标
import * as antIcons from '@ant-design/icons-vue'
//权限控制
import './router/permission'

const app = createApp(App)
app.use(createPinia())
app.use(router).use(Antd)

// 注册
Object.keys(antIcons).map(key => {
    app.component(key, antIcons[key as keyof typeof antIcons])
})

app.mount('#app')
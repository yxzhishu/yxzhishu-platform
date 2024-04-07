import axios from 'axios'
import { message as Amessage } from 'ant-design-vue';
import { useUserInfoStore } from '../stores/userInfo'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
//创建axios示例，添加全局配置
const service = axios.create({
    baseURL: '/api',
    timeout: 8000,
    //请求为跨域类型时是否在请求中协带cookie。
    withCredentials: true
})

//请求拦截
service.interceptors.request.use(req => {
    req.headers['Authorization'] = useUserInfoStore().user?.token || '';
    return req
}, error => {
    console.log(error);
    return Promise.reject(error)
})

//相应拦截
service.interceptors.response.use(res => {
    // 未设置状态码则默认成功状态
    const code = res.data.code || '00000'
    // 获取错误信息
    const msg = res.data.message || '网络异常，请稍后再试'

    //登陆状态异常码集合
    const tokenErrCode = ['A0201']

    if (code === '00000') {
        return res.data
    } else if (tokenErrCode.indexOf(code) > -1) {
        //清空的可能存在的已过期登陆信息
        sessionStorage.clear()
        setTimeout(() => {
            location.href = '/index'
        }, 800);
        Amessage.info('登录状态已失效，请重新登陆！' + msg)
        return Promise.reject('无效的会话，或者会话已过期，请重新登录' + msg)
    } else {
        Amessage.error(msg)
        return Promise.reject(msg);
    }
}, error => {
    console.log('err' + error);
    let { message } = error;
    if (message == "Network Error") {
        message = "后端接口连接异常";
    } else if (message.includes("timeout")) {
        message = "系统接口请求超时";
    } else if (message.includes("Request failed with status code")) {
        message = "系统接口(" + message.substr(message.length - 3) + ")异常";
    }
    Amessage.error(message)
    return Promise.reject(error)
})

export default service
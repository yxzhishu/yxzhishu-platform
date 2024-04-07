import request from '../../utils/request'

const baseUrl = '/system/account'

/**
 * 账号登出
 * @returns 
 */
export const apiDoLogout = () => {
    return request.get(baseUrl + '/doLogout')
}

/**
 * 登录
 * @param input 
 * @returns 
 */
export const apiDoLogin = (input: any) => {
    return request.post(baseUrl + '/doLogin', input)
}

/**
 * 切换角色
 * @param roleId 
 * @returns 
 */
export const apiSwitchRoles = (roleId: String | number) => {
    return request.get(baseUrl + '/switchRoles?roleId=' + roleId)
}

/**
 * 分页查询账户信息
 * @param param 
 * @returns 
 */
export const apiPage = (param: any) => {
    return request.post(baseUrl + '/page', param)
}

/**
 * 重置密码
 * @param param 
 * @returns 
 */
export const apiResetPassword = (param: any) => {
    return request.post(baseUrl + '/resetPassword', param)
}

/**
 * 修改数据
 * @param param 
 * @returns 
 */
export const apiUpdate = (param: any) => {
    return request.post(baseUrl + '/update', param)
}
/**
 * 注册
 * @param param 
 * @returns 
 */
export const apiRegister = (param: any) => {
    return request.post(baseUrl + '/register', param)
}
/**
 * 添加对应关系
 * @param param 
 * @returns 
 */
export const apiHandleVsInfo = (param: any) => {
    return request.post(baseUrl + '/handleVsInfo', param);
}
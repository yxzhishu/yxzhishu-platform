import request from '../../utils/request'

const baseUrl = '/system/role'

/**
 * 根据角色id获取角色信息
 * @returns 
 */
export const apiFindByRoleId = (param: any) => {
    return request.get(baseUrl + '/findByRoleId?roleIds' + param)
}

/**
 * 删除
 * @param param 
 * @returns 
 */
export const apiDelete = (param: any) => {
    return request.get(baseUrl + '/delete?roleId=' + param)
}
/**
 * 精确查询正常数据
 * @returns 
 */
export const apiFindNormalByAccurate = (param: any) => {
    return request.post(baseUrl + '/findNormalByAccurate', param)
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
 * 修改数据
 * @param param 
 * @returns 
 */
export const apiUpdate = (param: any) => {
    return request.post(baseUrl + '/update', param)
}
/**
 * 添加
 * @param param 
 * @returns 
 */
export const apiAdd = (param: any) => {
    return request.post(baseUrl + '/add', param)
}

/**
 * 授权处理
 * @param param 
 * @returns 
 */
export const apiHandleAuthorization = (param: any) => {
    return request.post(baseUrl + '/handleAuthorization', param)
}
import request from '../../utils/request'

const baseUrl = '/system/menu'

/**
 * 获取路由信息
 * @returns 
 */
export const apiFindRoute = () => {
    return request.get(baseUrl + '/findRoute')
}

/**
 * 查询序列化菜单信息
 * @param param 
 * @returns 
 */
export const apiFindMenuBySerialize = () => {
    return request.get(baseUrl + '/findMenuBySerialize')
}

/**
 * 查询角色对应的菜单id
 * @param param 
 * @returns 
 */
export const apiFindMenuIdByRoleId = (row: any) => {
    return request.get(baseUrl + '/findMenuIdByRoleId?roleId=' + row)
}
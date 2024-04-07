import request from '../../utils/request'

const baseUrl = '/system/log'

/**
 * 分页查询信息
 * @param param 
 * @returns 
 */
export const apiProblemPage = (param: any) => {
    return request.post(baseUrl + '/problemPage', param)
}

/**
 * 回答问题
 * @param param 
 * @returns 
 */
export const apiProblemAnswer = (param: any) => {
    return request.post(baseUrl + '/problemAnswer', param)
}
/**
 * 添加问题
 * @param param 
 * @returns 
 */
export const apiProblemPropose = (param: any) => {
    return request.post(baseUrl + '/problemPropose', param)
}

/**
 * 删除问题
 * @param param 
 * @returns 
 */
export const apiProblemDelete = (param: any) => {
    return request.get(baseUrl + '/problemDelete?id=' + param)
}
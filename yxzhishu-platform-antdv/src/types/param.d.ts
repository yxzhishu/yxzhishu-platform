/**
 * 检索参数
 */
export interface SearchParam {
    search?: string,
    current: number,
    pageSize: number,
    total: number,
    status?: string,
    pageSizeOptions: Array<string | undefined>,
}

export interface Role {
    deptId: string,
    deptName: string,
    description: string,
    roleId: string,
    roleKey: string,
    roleName: string
}
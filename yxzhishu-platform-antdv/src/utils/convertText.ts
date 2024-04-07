export const getStatusText = (status: string) => {
    switch (status) {
        case '0':
            return '正常';
        case '1':
            return '禁用';
        default:
            return status;
    }
};

export const getRoleTypeText = (roleType: string) => {
    switch (roleType) {
        case '0':
            return '管理';
        case '1':
            return '用户';
        default:
            return roleType;
    }
}
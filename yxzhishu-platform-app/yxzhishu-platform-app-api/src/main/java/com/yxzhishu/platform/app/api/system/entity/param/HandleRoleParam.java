package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

/**
 * @classDesc: 角色处理入参
 * @author: 王朔
 * @date: 2023/11/28 13:49
 * @remark:
 */
@Data
public class HandleRoleParam {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限字符
     */
    private String roleKey;
    /**
     * 排序号
     */
    private Integer sortOrder;
    /**
     * 状态
     */
    private String status;
    /**
     * 其他信息
     */
    private String description;

}

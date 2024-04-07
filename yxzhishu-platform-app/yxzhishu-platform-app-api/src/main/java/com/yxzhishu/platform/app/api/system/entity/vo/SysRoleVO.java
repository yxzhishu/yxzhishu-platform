package com.yxzhishu.platform.app.api.system.entity.vo;

import lombok.Data;

/**
 * @classDesc: 角色信息
 * @author: 王朔
 * @date: 2023/12/27 9:44
 * @remark:
 */
@Data
public class SysRoleVO {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标识
     */
    private String roleKey;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 排序
     */
    private Short sortOrder;
    /**
     * 状态
     */
    private String status;
    /**
     * 说明
     */
    private String description;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;
}

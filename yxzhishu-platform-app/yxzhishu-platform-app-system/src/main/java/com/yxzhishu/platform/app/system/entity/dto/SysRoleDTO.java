package com.yxzhishu.platform.app.system.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/27 9:20
 * @remark:
 */
@Data
public class SysRoleDTO {
    /**
     * 账号ID
     */
    private Long accountId;
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
     * 角色类型(0:管理,1:普通用户)
     */
    private String roleType;
    /**
     * 排序号
     */
    private Short sortOrder;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建者ID
     */
    private Long createrId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 备注
     */
    private String description;
    /**
     * 系统标识
     */
    private String systemFlag;
}

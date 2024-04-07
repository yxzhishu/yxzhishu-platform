package com.yxzhishu.platform.app.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @classDesc: 角色表
 * @author: 王朔
 * @date: 2023/12/25 16:56
 * @remark:
 */
@Data
@TableName("sys.role")
public class SysRole {
    /**
     * 角色ID
     */
    @TableId
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

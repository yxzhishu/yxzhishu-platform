package com.yxzhishu.platform.app.api.system.entity.vo;

import lombok.Data;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/1 10:19
 * @remark:
 */
@Data
public class SysDeptVO {
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 排序号(越小越优先)
     */
    private Short sortOrder;
    /**
     * 状态(0正常,1停用)
     */
    private String status;
    /**
     * 系统标识
     */
    private String systemFlag;
    /**
     * 说明
     */
    private String description;
}

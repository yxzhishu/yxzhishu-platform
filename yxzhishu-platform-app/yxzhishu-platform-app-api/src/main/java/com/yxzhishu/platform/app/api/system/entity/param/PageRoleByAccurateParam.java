package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import java.util.List;

/**
 * @classDesc: 菜单分页精确查询参数
 * @author: 王朔
 * @date: 2023/12/29 10:24
 * @remark:
 */
@Data
public class PageRoleByAccurateParam extends PageSearchParam {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 账户id集合
     */
    private List<Long> accountIdList;
    /**
     * 角色id集合
     */
    private List<Long> roleIdList;
}

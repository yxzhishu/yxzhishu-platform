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
public class PageMenuByAccurateParam extends PageSearchParam {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色Id集合
     */
    private List<Long> roleIdList;
    /**
     * 菜单名称集合
     */
    private List<String> menuNameList;
    /**
     * 菜单ID集合
     */
    private List<Long>  menuIdList;
}

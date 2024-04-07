package com.yxzhishu.platform.app.system.service;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/4 18:31
 * @remark:
 */
public interface SysRoleVsMenuService {
    /**
     * 修改对应关系
     * @param roleId
     * @param menuIds
     * @return
     */
    void updateVsInfo(Long roleId, List<Long> menuIds);
}

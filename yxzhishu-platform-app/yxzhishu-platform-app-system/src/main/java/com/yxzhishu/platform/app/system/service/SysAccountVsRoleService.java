package com.yxzhishu.platform.app.system.service;

import com.yxzhishu.platform.app.system.entity.po.SysAccountVsRole;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/29 15:36
 * @remark:
 */
public interface SysAccountVsRoleService {
    /**
     * 修改对应关系
     * @param accountId
     * @param roleIds
     * @return
     */
    void updateVsInfo(Long accountId, List<Long> roleIds);

    /**
     * 删除对应关系
     * @param accountId
     * @param roleId
     * @return
     */
    int deleteVsInfo(Long accountId,Long roleId);

    List<SysAccountVsRole> findData(Long accountId,Long roleId);
}

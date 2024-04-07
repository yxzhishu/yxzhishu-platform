package com.yxzhishu.platform.app.system.service;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageRoleByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.system.entity.dto.SysRoleDTO;
import com.yxzhishu.platform.app.system.entity.po.SysRole;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/18 11:45
 * @remark:
 */
public interface SysRoleService {
    /**
     * 分页查询角色信息
     * @param param
     * @return
     */
    PageInfo<SysRoleDTO> page(PageSearchParam param);

    /**
     * 添加角色
     * @param role
     */
    int add(SysRole role);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    int update(SysRole role);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    int delete(Long roleId);

    /**
     * 根据角色ID列表查询所属角色信息
     * @param roleIdList
     * @return
     */
    List<SysRoleDTO> findByRoleId(List<Long> roleIdList);

    /**
     * 精确查询
     * @param param
     * @return
     */
    List<SysRoleDTO> findByAccurate(PageRoleByAccurateParam param);
}

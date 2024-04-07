package com.yxzhishu.platform.app.system.business;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageRoleByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleVsMenuParam;
import com.yxzhishu.platform.app.api.system.entity.vo.SysRoleVO;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/15 17:10
 * @remark:
 */
public interface SysRoleBusiness {

    /**
     * 分页查询角色信息
     * @param param
     * @return
     */
    PageInfo<SysRoleVO> page(PageSearchParam param);

    /**
     * 添加角色
     * @param param
     */
    void add(HandleRoleParam param);

    /**
     * 修改角色信息
     * @param param
     * @return
     */
    void update(HandleRoleParam param);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    void delete(Long roleId);

    /**
     * 根据角色ID列表查询所属角色信息
     * @param roleIdList
     * @return
     */
    List<SysRoleVO> findByRoleId(List<Long> roleIdList);

    /**
     * 精确查询
     * @param param
     * @return
     */
    List<SysRoleVO> findByAccurate(PageRoleByAccurateParam param) ;

    /**
     * 精确查询正常数据
     * @param param
     * @return
     */
    List<SysRoleVO> findNormalByAccurate(PageRoleByAccurateParam param) ;

    /**
     * 授权
     * @param param
     */
    void handleAuthorization(HandleRoleVsMenuParam param);
}

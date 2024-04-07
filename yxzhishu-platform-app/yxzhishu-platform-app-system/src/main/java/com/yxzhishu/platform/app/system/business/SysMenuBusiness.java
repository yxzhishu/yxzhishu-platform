package com.yxzhishu.platform.app.system.business;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.vo.RouteVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysMenuVO;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/15 13:53
 * @remark:
 */
public interface SysMenuBusiness {

    /**
     * 获取所有菜单信息
     * @return
     */
    List<SysMenuVO> findMenu();

    /**
     * 获取路由信息
     * @return
     */
    List<RouteVO> findRoute();

    /**
     * 根据角色id查询菜单id列表
     * @param roleId
     * @return
     */
    List<Long> findMenuIdByRoleId(Long roleId);

    /**
     * 分页查询
     * @param param
     * @return
     */
    PageInfo<SysMenuVO> page(PageSearchParam param);

    List<SysMenuVO> findMenuBySerialize();
}

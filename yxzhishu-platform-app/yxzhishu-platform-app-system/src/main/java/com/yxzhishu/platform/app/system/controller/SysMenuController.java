package com.yxzhishu.platform.app.system.controller;

import com.yxzhishu.platform.app.api.system.SysMenuApi;
import com.yxzhishu.platform.app.api.system.entity.vo.RouteVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysMenuVO;
import com.yxzhishu.platform.app.base.response.R;
import com.yxzhishu.platform.app.system.business.SysMenuBusiness;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/29 10:40
 * @remark:
 */
@RestController
public class SysMenuController implements SysMenuApi {
    @Resource
    SysMenuBusiness sysMenuBusiness;

    /**
     * 查询菜单列表
     * @return
     */
    @Override
    public R<List<SysMenuVO>> findMenu() {
        List<SysMenuVO> list = sysMenuBusiness.findMenu();
        return R.success(list);
    }

    @Override
    public R<List<SysMenuVO>> findMenuBySerialize() {
        List<SysMenuVO> list = sysMenuBusiness.findMenuBySerialize();
        return R.success(list);
    }

    /**
     * 获取路由信息
     * @return
     */
    @Override
    public R<List<RouteVO>> findRoute() {
        List<RouteVO> list = sysMenuBusiness.findRoute();
        return R.success(list);
    }

    /**
     * 根据角色id查询菜单id列表
     *
     * @param roleId
     * @return
     */
    @Override
    public R<List<Long>> findMenuIdByRoleId(Long roleId) {
        List<Long> list = sysMenuBusiness.findMenuIdByRoleId(roleId);
        return R.success(list);
    }

}

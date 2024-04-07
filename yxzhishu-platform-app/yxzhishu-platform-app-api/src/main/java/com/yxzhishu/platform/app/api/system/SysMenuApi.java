package com.yxzhishu.platform.app.api.system;

import com.yxzhishu.platform.app.api.system.entity.vo.RouteVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysMenuVO;
import com.yxzhishu.platform.app.base.response.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/11/28 14:01
 * @remark:
 */
@RequestMapping("/system/menu")
public interface SysMenuApi {
    /**
     * 查询菜单列表
     * @return
     */
    @GetMapping("/findMenu")
    R<List<SysMenuVO>> findMenu();

    /**
     * 查询菜单列表(序列化)
     * @return
     */
    @GetMapping("/findMenuBySerialize")
    R<List<SysMenuVO>> findMenuBySerialize();

    /**
     * 获取路由信息
     * @return
     */
    @GetMapping("/findRoute")
    R<List<RouteVO>> findRoute();

    /**
     * 根据角色id查询菜单id列表
     * @param roleId
     * @return
     */
    @GetMapping("/findMenuIdByRoleId")
    R<List<Long>> findMenuIdByRoleId(@RequestParam("roleId") Long roleId);
}

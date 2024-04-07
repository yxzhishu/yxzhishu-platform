package com.yxzhishu.platform.app.system.business.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.yxzhishu.platform.app.api.system.entity.param.PageMenuByAccurateParam;
import com.yxzhishu.platform.app.base.constant.CommonConst;
import com.yxzhishu.platform.app.system.entity.dto.SysMenuDTO;
import com.yxzhishu.platform.app.system.entity.po.SysAccountVsRole;
import com.yxzhishu.platform.app.system.service.SysAccountVsRoleService;
import com.yxzhishu.platform.app.system.service.SysMenuService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/11 8:53
 * @remark:
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    SysMenuService sysMenuService;
    @Resource
    SysAccountVsRoleService sysAccountVsRoleService;
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        //角色信息
        Long accountId = Long.parseLong(loginId.toString());
        List<SysAccountVsRole> list = sysAccountVsRoleService.findData(accountId,null);
        List<Long> roleIdList = list.stream().map(item->item.getRoleId()).collect(Collectors.toList());
        //最高权限包含所有权限
        if (roleIdList.contains(CommonConst.LONG_ZERO)){
            return Collections.singletonList("*");
        }
        //菜单信息
        PageMenuByAccurateParam param = new PageMenuByAccurateParam();
        param.setRoleIdList(roleIdList);
        List<SysMenuDTO> menuList = sysMenuService.findByAccurate(param);
        return  menuList.stream().map(SysMenuDTO::getMenuName).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Long accountId = Long.parseLong(loginId.toString());
        List<SysAccountVsRole> list = sysAccountVsRoleService.findData(accountId,null);
        return list.stream().map(item->item.getRoleId().toString()).collect(Collectors.toList());
    }
}

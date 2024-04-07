package com.yxzhishu.platform.app.system.service.impl;

import com.yxzhishu.platform.app.base.constant.CommonConst;
import com.yxzhishu.platform.app.system.entity.po.SysRoleVsMenu;
import com.yxzhishu.platform.app.system.mapper.SysRoleVsMenuMapper;
import com.yxzhishu.platform.app.system.service.SysRoleVsMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/4 18:31
 * @remark:
 */
@Service
public class SysRoleVsMenuServiceImpl implements SysRoleVsMenuService {
    @Resource
    private SysRoleVsMenuMapper sysRoleVsMenuMapper;


    @Override
    public void updateVsInfo(Long roleId, List<Long> menuIds) {
        SysRoleVsMenu sysRoleVsMenu = new SysRoleVsMenu();
        sysRoleVsMenu.setRoleId(roleId);


        List<SysRoleVsMenu> list = sysRoleVsMenuMapper.findData(roleId,null);
        List<Long> ids =list.stream().map(SysRoleVsMenu::getMenuId).collect(Collectors.toList());
        List<Long> deleteIds = ids.stream().filter(item->!menuIds.contains(item)).collect(Collectors.toList());
        for (Long id:deleteIds){
            sysRoleVsMenuMapper.deleteVsInfo(roleId,id);
        }
        List<Long> insertIds = menuIds.stream().filter(item->!ids.contains(item)&&!item.equals(CommonConst.LONG_ZERO)).collect(Collectors.toList());
        for (Long id:insertIds){
            sysRoleVsMenu.setMenuId(id);
            sysRoleVsMenuMapper.insert(sysRoleVsMenu);
        }
    }
}

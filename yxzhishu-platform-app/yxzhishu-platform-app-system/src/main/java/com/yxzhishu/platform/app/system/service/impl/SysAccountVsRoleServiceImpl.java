package com.yxzhishu.platform.app.system.service.impl;

import com.yxzhishu.platform.app.base.constant.CommonConst;
import com.yxzhishu.platform.app.system.entity.po.SysAccountVsRole;
import com.yxzhishu.platform.app.system.mapper.SysAccountVsRoleMapper;
import com.yxzhishu.platform.app.system.service.SysAccountVsRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/20 9:00
 * @remark:
 */
@Service
public class SysAccountVsRoleServiceImpl implements SysAccountVsRoleService {
    @Resource
    SysAccountVsRoleMapper sysAccountVsRoleMapper;

    @Override
    public void updateVsInfo(Long accountId, List<Long> roleIds) {
        SysAccountVsRole sysAccountVsRole = new SysAccountVsRole();
        sysAccountVsRole.setAccountId(accountId);

        List<SysAccountVsRole> list = sysAccountVsRoleMapper.findData(accountId,null);
        List<Long> ids =list.stream().map(SysAccountVsRole::getRoleId).collect(Collectors.toList());
        List<Long> deleteIds = ids.stream().filter(item->!roleIds.contains(item)).collect(Collectors.toList());
        for (Long id:deleteIds){
            sysAccountVsRoleMapper.deleteVsInfo(accountId,id);
        }
        List<Long> insertIds = roleIds.stream().filter(item->!ids.contains(item)&&!item.equals(CommonConst.LONG_ZERO)).collect(Collectors.toList());
        for (Long id:insertIds){
            sysAccountVsRole.setRoleId(id);
            sysAccountVsRoleMapper.insert(sysAccountVsRole);
        }
    }

    @Override
    public int deleteVsInfo(Long accountId, Long roleId) {
        return sysAccountVsRoleMapper.deleteVsInfo(accountId,roleId);
    }

    @Override
    public List<SysAccountVsRole> findData(Long accountId, Long roleId) {
        return sysAccountVsRoleMapper.findData(accountId,roleId);
    }
}

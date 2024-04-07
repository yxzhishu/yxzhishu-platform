package com.yxzhishu.platform.app.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageRoleByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.base.enums.ErrorCodeEnum;
import com.yxzhishu.platform.app.base.exception.YxException;
import com.yxzhishu.platform.app.system.entity.dto.SysRoleDTO;
import com.yxzhishu.platform.app.system.entity.po.SysRole;
import com.yxzhishu.platform.app.system.mapper.SysRoleMapper;
import com.yxzhishu.platform.app.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/20 9:03
 * @remark:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRoleDTO> page(PageSearchParam param) {
        Page<SysRoleDTO> page= PageHelper.startPage(param.getCurrent(), param.getPageSize());
        List<SysRoleDTO> list = sysRoleMapper.findRole(param.getSearch(),param.getStatus());

        return page.toPageInfo();
    }

    @Override
    public int add(SysRole role) {
        role.setRoleId(null);
        return sysRoleMapper.insert(role);
    }

    @Override
    public int update(SysRole role) {
        if (ObjectUtils.isEmpty(role.getRoleId())){
            throw new YxException(ErrorCodeEnum.SERVER_PARAM_ERROR);
        }
        return sysRoleMapper.updateById(role);
    }

    @Override
    public int delete(Long roleId) {
        return sysRoleMapper.deleteById(roleId);
    }

    @Override
    public List<SysRoleDTO> findByRoleId(List<Long> roleIdList) {

        List<SysRoleDTO> list = sysRoleMapper.findByRoleId(roleIdList);
        return list;
    }

    @Override
    public List<SysRoleDTO> findByAccurate(PageRoleByAccurateParam param) {
        return sysRoleMapper.findByAccurate(param);
    }
}

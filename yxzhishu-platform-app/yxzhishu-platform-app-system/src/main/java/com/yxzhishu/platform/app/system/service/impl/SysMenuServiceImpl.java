package com.yxzhishu.platform.app.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageMenuByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.system.entity.dto.SysMenuDTO;
import com.yxzhishu.platform.app.system.mapper.SysMenuMapper;
import com.yxzhishu.platform.app.system.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/20 9:02
 * @remark:
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuDTO> findMenu() {
        List<SysMenuDTO> list = sysMenuMapper.findMenu(null,null);
        return list;
    }

    /**
     * 精确查询
     *
     * @param param
     * @return
     */
    @Override
    public List<SysMenuDTO> findByAccurate(PageMenuByAccurateParam param) {
        return sysMenuMapper.findByAccurate(param);
    }

    @Override
    public PageInfo<SysMenuDTO> page(PageSearchParam param) {
        Page<SysMenuDTO> page= PageHelper.startPage(param.getCurrent(), param.getPageSize());
        List<SysMenuDTO> list = sysMenuMapper.findMenu(param.getSearch(),param.getStatus());

        return page.toPageInfo();
    }

}

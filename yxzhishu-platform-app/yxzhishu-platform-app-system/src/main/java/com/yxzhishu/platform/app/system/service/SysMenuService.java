package com.yxzhishu.platform.app.system.service;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageMenuByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.system.entity.dto.SysMenuDTO;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/29 10:48
 * @remark:
 */
public interface SysMenuService {
    /**
     * 获取所有菜单信息
     * @return
     */
    List<SysMenuDTO> findMenu();

    /**
     * 精确查询
     * @param param
     * @return
     */
    List<SysMenuDTO> findByAccurate(PageMenuByAccurateParam param);

    /**
     * 分页查询角色信息
     * @param param
     * @return
     */
    PageInfo<SysMenuDTO> page(PageSearchParam param);
}

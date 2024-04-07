package com.yxzhishu.platform.app.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxzhishu.platform.app.api.system.entity.param.PageMenuByAccurateParam;
import com.yxzhishu.platform.app.system.entity.dto.SysMenuDTO;
import com.yxzhishu.platform.app.system.entity.po.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/29 10:41
 * @remark:
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 查询菜单信息
     * @param search 检索条件
     * @param status 状态
     * @return
     */
    List<SysMenuDTO> findMenu(@Param("search") String search, @Param("status") String status);

    /**
     * 精确查询用户信息
     * @param param
     * @return
     */
    List<SysMenuDTO> findByAccurate(PageMenuByAccurateParam param);
}

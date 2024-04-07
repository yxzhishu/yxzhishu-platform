package com.yxzhishu.platform.app.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxzhishu.platform.app.system.entity.po.SysRoleVsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/26 15:10
 * @remark:
 */
@Mapper
public interface SysRoleVsMenuMapper extends BaseMapper<SysRoleVsMenu> {
    /**
     * 删除对应关系
     * @param roleId
     * @param menuId
     * @return
     */
    int deleteVsInfo(@Param("roleId") Long roleId,@Param("menuId") Long menuId);

    /**
     * 根据角色id查询对应信息
     * @param roleId
     * @param menuId
     * @return
     */
    List<SysRoleVsMenu> findData(@Param("roleId") Long roleId,@Param("menuId") Long menuId);
}

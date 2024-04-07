package com.yxzhishu.platform.app.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxzhishu.platform.app.api.system.entity.param.PageRoleByAccurateParam;
import com.yxzhishu.platform.app.system.entity.dto.SysRoleDTO;
import com.yxzhishu.platform.app.system.entity.po.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/27 9:50
 * @remark:
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 查询角色信息
     * @param search
     * @param status
     * @return
     */
    List<SysRoleDTO> findRole(@Param("search") String search, @Param("status") String status);

    /**
     * 根据角色id列表查询角色信息
     * @param roleIdList
     * @return
     */
    List<SysRoleDTO> findByRoleId(@Param("roleIdList") List<Long> roleIdList);

    /**
     * 精确查询角色信息
     * @param param
     * @return
     */
    List<SysRoleDTO> findByAccurate(PageRoleByAccurateParam param);

}

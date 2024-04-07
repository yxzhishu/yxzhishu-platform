package com.yxzhishu.platform.app.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxzhishu.platform.app.system.entity.po.SysAccountVsRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/26 15:08
 * @remark:
 */
@Mapper
public interface SysAccountVsRoleMapper extends BaseMapper<SysAccountVsRole> {
    /**
     * 删除对应关系
     * @param accountId
     * @param roleId
     * @return
     */
    int deleteVsInfo(@Param("accountId") Long accountId,@Param("roleId") Long roleId);

    /**
     * 根据账号id查询对应信息
     * @param accountId
     * @return
     */
    List<SysAccountVsRole> findData(@Param("accountId") Long accountId,@Param("roleId") Long roleId);
}

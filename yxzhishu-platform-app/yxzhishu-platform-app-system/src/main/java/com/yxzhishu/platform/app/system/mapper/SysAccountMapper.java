package com.yxzhishu.platform.app.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxzhishu.platform.app.api.system.entity.param.PageAccountByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.system.entity.dto.SysAccountDTO;
import com.yxzhishu.platform.app.system.entity.po.SysAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAccountMapper extends BaseMapper<SysAccount> {
    /**
     * 查询账号信息
     * @param param 检索条件
     * @return
     */
    List<SysAccountDTO> findAccount(PageSearchParam param);

    /**
     * 精确查询用户信息
     * @param param
     * @return
     */
    List<SysAccountDTO> findByAccurate(PageAccountByAccurateParam param);
}

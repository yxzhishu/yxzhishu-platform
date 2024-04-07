package com.yxzhishu.platform.app.system.service;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageAccountByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.system.entity.dto.SysAccountDTO;
import com.yxzhishu.platform.app.system.entity.po.SysAccount;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/18 10:16
 * @remark:
 */
public interface SysAccountService {

    /**
     * 添加账户并返回主键
     * @param account
     * @return
     */
    Long add(SysAccount account);

    /**
     * 修改账户信息(用户修改头像，手机号等 管理员修改账号状态备注等)
     * @param account
     */
    int update(SysAccount account);

    /**
     * 分页查询账号信息
     * @param param
     * @return
     */
    PageInfo<SysAccountDTO> page(PageSearchParam param);

    /**
     * 分页精确查询
     * @param param
     * @return
     */
    PageInfo<SysAccountDTO> pageByAccurate(PageAccountByAccurateParam param);

    /**
     * 精确查询
     * @param param
     * @return
     */
    List<SysAccountDTO> findByAccurate(PageAccountByAccurateParam param);

}

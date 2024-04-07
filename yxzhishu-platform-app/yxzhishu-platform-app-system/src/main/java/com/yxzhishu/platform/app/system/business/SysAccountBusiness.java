package com.yxzhishu.platform.app.system.business;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.*;
import com.yxzhishu.platform.app.api.system.entity.vo.DoLoginVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysAccountVO;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/29 13:56
 * @remark:
 */
public interface SysAccountBusiness {

    /**
     * 登录
     * @param param
     * @return
     */
    DoLoginVO doLogin(DoLoginParam param);

    /**
     * 注册
     * @param param
     * @return
     */
    void register(RegisterParam param);

    /**
     * 修改密码
     * @param param
     */
    void updatePassword(UpdatePasswrodParam param);

    /**
     * 修改账户信息
     * @param param
     */
    void update(UpdateAccountParam param);

    /**
     * 分页查询账户信息
     * @param param
     * @return
     */
    PageInfo<SysAccountVO> page(PageSearchParam param);

    /**
     * 分页精确查询
     * @param param
     * @return
     */
    PageInfo<SysAccountVO> pageByAccurate(PageAccountByAccurateParam param);
}

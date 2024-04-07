package com.yxzhishu.platform.app.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.SysAccountApi;
import com.yxzhishu.platform.app.api.system.entity.param.*;
import com.yxzhishu.platform.app.api.system.entity.vo.DoLoginVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysAccountVO;
import com.yxzhishu.platform.app.base.response.R;
import com.yxzhishu.platform.app.system.business.SysAccountBusiness;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/11/21 13:53
 * @remark:
 */
@RestController
public class SysAccountController implements SysAccountApi {
    @Resource
    SysAccountBusiness sysAccountBusiness;

    @Override
    public R<String> doLogout() {
        StpUtil.logout();
        return R.success("已退出登录");
    }
    @Override
    public R<DoLoginVO> doLogin(DoLoginParam param) {
        DoLoginVO vo = sysAccountBusiness.doLogin(param);
        return R.success(vo);
    }

    @Override
    public R<String> register(RegisterParam param) {
        sysAccountBusiness.register(param);
        return R.success("注册成功");
    }

    @Override
    public R<String> updatePassword(UpdatePasswrodParam param) {
        param.setUpdateFlag(1);
        sysAccountBusiness.updatePassword(param);
        return R.success("已修改密码");
    }

    @Override
    public R<String> resetPassword(UpdatePasswrodParam param) {
        param.setUpdateFlag(0);
        sysAccountBusiness.updatePassword(param);
        return R.success("已重置密码");
    }

    @Override
    public R<String> update(UpdateAccountParam param) {
        sysAccountBusiness.update(param);
        return R.success("已更新");
    }

    @Override
    public R<String> renew(UpdateAccountParam param) {
        //未完成
        return R.success("已更新");
    }

    @Override
    public R<PageInfo<SysAccountVO>> page(PageSearchParam param) {
        PageInfo<SysAccountVO> pageInfo = sysAccountBusiness.page(param);
        return R.success(pageInfo);
    }

    @Override
    public R<PageInfo<SysAccountVO>> pageByAccurate(PageAccountByAccurateParam param) {
        PageInfo<SysAccountVO> pageInfo = sysAccountBusiness.pageByAccurate(param);
        return R.success(pageInfo);
    }
}

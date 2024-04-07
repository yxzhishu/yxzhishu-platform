package com.yxzhishu.platform.app.api.system;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.*;
import com.yxzhishu.platform.app.api.system.entity.vo.DoLoginVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysAccountVO;
import com.yxzhishu.platform.app.base.response.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @classDesc: 账号
 * @author: 王朔
 * @date: 2023/11/21 13:43
 * @remark:
 */
@RequestMapping("/system/account")
public interface SysAccountApi {
    /**
     * 账号登出
     */
    @GetMapping("/doLogout")
    R<String> doLogout();

    /**
     * 登录
     *
     * @param param 登录入参
     */
    @PostMapping("/doLogin")
    R<DoLoginVO> doLogin(@RequestBody @Validated DoLoginParam param);

    /**
     * 注册
     *
     * @param param
     * @return
     */
    @PostMapping("/register")
    R<String> register(@RequestBody @Validated RegisterParam param);

    /**
     * 修改密码
     *
     * @param param
     * @return
     */
    @PostMapping("/updatePassword")
    R<String> updatePassword(@RequestBody @Validated UpdatePasswrodParam param);

    /**
     * 重置密码
     *
     * @param param
     * @return
     */
    @PostMapping("/resetPassword")
    R<String> resetPassword(@RequestBody @Validated UpdatePasswrodParam param);

    /**
     * 修改账户信息（账户管理）
     *
     * @param param
     * @return
     */
    @PostMapping("/update")
    R<String> update(@RequestBody @Validated UpdateAccountParam param);

    /**
     * 更新账户信息（用户更新）
     * @param param
     * @return
     */
    @PostMapping("/renew")
    R<String> renew(@RequestBody @Validated UpdateAccountParam param);

    /**
     * 分页查询账户信息
     *
     * @param param
     * @return
     */
    @PostMapping("/page")
    R<PageInfo<SysAccountVO>> page(@RequestBody @Validated PageSearchParam param);

    /**
     * 分页精确查询用户信息（查询角色下对应的账号信息等）
     * @param param
     * @return
     */
    @PostMapping("/pageByAccurate")
    R<PageInfo<SysAccountVO>> pageByAccurate(@RequestBody @Validated PageAccountByAccurateParam param);
}

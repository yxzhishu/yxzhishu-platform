package com.yxzhishu.platform.app.api.system.entity.vo;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.Data;

/**
 * @ClassName DoLoginVO
 * @description: 登陆返回VO
 * @author: 王朔
 * @create: 2023-11-26 11:56
 * @Version 1.0
 **/
@Data
public class DoLoginVO {
    /**
     * 账号信息
     */
    private SysAccountVO accountInfo;
    /**
     * token信息
     */
    private SaTokenInfo tokenInfo;
}

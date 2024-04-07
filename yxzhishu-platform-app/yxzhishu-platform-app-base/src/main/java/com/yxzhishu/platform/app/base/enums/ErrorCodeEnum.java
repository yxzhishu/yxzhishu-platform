package com.yxzhishu.platform.app.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @classDesc: 异常码枚举
 * @author: 王朔
 * @date: 2023/11/9 9:58
 * @remark:
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    /**
     * 成功
     */
    SUCCESS("00000","成功!"),


    /**
     * 客户端错误
     */
    CLIENT_ERROR("A0001","客户端错误!"),
    /**
     * 账号错误
     */
    CLIENT_ACCOUNT_ERROR("A0100","账户异常!"),
    /**
     * TOKEN异常
     */
    CLIENT_TOKEN_ERROR("A0200","TOKEN异常!"),
    /**
     * TOKEN异常
     */
    CLIENT_TOKEN_NOT("A0201","登录状态失效!"),
    /**
     * 权限错误
     */
    CLIENT_ROLE_ERROR("A0300","权限错误!"),
    /**
     * 存在关联数据
     */
    CLIENT_ASSOCIATED_DATA("A0400","存在关联数据"),
    /**
     * 客户端请求错误
     */
    CLIENT_REQUEST_ERROR("A0500","客户端请求错误"),


    /**
     * 服务端错误
     */
    SERVER_ERROR("B0001","服务端错误!"),
    /**
     * SQL执行异常
     */
    SERVER_SQL_ABNORMAL("B0100","SQL执行异常!"),
    /**
     * 服务数据异常
     */
    SERVER_DATA_ERROR("B0200","服务数据异常!"),
    /**
     * 服务参数异常
     */
    SERVER_PARAM_ERROR("B0201","服务参数异常!"),
    /**
     * 服务执行失败
     */
    SERVER_EXEC_FAIL("B0300","服务执行失败!"),


    /**
     * 第三方服务错误
     */
    PROVIDER_ERROR("C0001","第三方服务错误!"),


    ;
    /**
     * 编码
     */
    private String code;
    /**
     * 消息
     */
    private String message;
}

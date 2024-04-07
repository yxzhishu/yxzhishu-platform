package com.yxzhishu.platform.app.system.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/13 17:16
 * @remark:
 */
@Data
public class SysAccountDTO {
    /**
     * 账号ID
     */
    private Long accountId;
    /**
     *昵称
     */
    private String nickname;
    /**
     * 账号
     */
    private String accountCode;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 账号状态
     */
    private String status;
    /**
     * 系统标识
     */
    private String systemFlag;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建者
     */
    private Long createrId;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;
}

package com.yxzhishu.platform.app.system.entity.po;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName 账户表
 * @description:
 * @author: 王朔
 * @create: 2023-11-26 13:28
 * @Version 1.0
 **/
@Data
@TableName("sys.account")
public class SysAccount {
    /**
     * 账号ID
     */
    @TableId
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
     * 用户ID
     */
    private Long userId;
    /**
     * 创建者
     */
    private Long createrId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;
    /**
     * 最后登陆IP
     */
    private String lastLoginIp;
    {
        this.createTime = new Date();
        this.createrId = StpUtil.isLogin()?Long.parseLong(StpUtil.getLoginId().toString().split("-")[0]):-1L;
    }
}

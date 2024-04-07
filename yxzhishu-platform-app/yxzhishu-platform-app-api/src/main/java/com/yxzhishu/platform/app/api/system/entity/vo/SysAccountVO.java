package com.yxzhishu.platform.app.api.system.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AccountVO
 * @description:
 * @author: 王朔
 * @create: 2023-11-26 11:57
 * @Version 1.0
 **/
@Data
public class SysAccountVO {
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
     * 头像
     */
    private String avatar;
    /**
     * 账户状态
     */
    private String status;
    /**
     * 其他
     */
    private String description;
    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 所属角色集合
     */
    private List<Long> roleIdList;

}

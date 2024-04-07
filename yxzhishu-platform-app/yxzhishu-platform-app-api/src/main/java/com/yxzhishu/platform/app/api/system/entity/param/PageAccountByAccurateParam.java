package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import java.util.List;

/**
 * @classDesc: 账号分页精确查询参数
 * @author: 王朔
 * @date: 2023/12/29 10:24
 * @remark:
 */
@Data
public class PageAccountByAccurateParam extends PageSearchParam {
    /**
     * 账号id
     */
    private Long accountId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 登录名
     */
    private String accountCode;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 账号id集合
     */
    private List<Long> accountIdList;
    /**
     * 角色id集合
     */
    private List<Long> roleIdList;
}

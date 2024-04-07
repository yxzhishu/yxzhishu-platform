package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

/**
 * @ClassName UpdatePasswrodParam
 * @description: 修改/重置密码入参
 * @author: 王朔
 * @create: 2023-11-26 12:45
 * @Version 1.0
 **/
@Data
public class UpdatePasswrodParam {
    /**
     * 账号ID
     */
    private Long accountId;

    /**
     * 密码
     */
    private String password;

    /**
     * 老密码
     */
    private String oldPassword;

    /**
     * 修改标识（1修改，0重置）,非前端传入
     */
    private Integer updateFlag;
}

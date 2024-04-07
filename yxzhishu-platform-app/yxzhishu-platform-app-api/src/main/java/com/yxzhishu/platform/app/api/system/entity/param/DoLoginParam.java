package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName DoLoginParam
 * @description: 登录入参
 * @author: 王朔
 * @create: 2023-11-26 12:35
 * @Version 1.0
 **/
@Data
public class DoLoginParam {
    /**
     * 账号(用户名、手机号、邮箱)
     */
    @NotBlank(message ="账号不能为空")
    @Size(min = 2,message = "账号长度不正确")
    private String accountCode;
    /**
     * 密码
     */
    @NotBlank(message ="密码不能为空")
    @Size(min = 4,max = 30,message = "密码长度不正确")
    private String password;
}

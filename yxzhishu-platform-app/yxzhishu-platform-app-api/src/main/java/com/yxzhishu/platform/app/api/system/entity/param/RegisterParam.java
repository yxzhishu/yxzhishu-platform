package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @ClassName RegisterParam
 * @description: 注册入参
 * @author: 王朔
 * @create: 2023-11-26 12:42
 * @Version 1.0
 **/
@Data
public class RegisterParam {
    /**
     * 账号
     */
    @NotEmpty(message ="账号不能为空")
    @Size(min = 2,message = "账号长度不正确")
    private String accountCode;

    /**
     * 密码
     */
    private String password;

    /**
     *昵称
     */
    private String nickname;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建者ID
     */
    private Long createrId;
    /**
     * 其他说明
     */
    private String description;
    /**
     * 所属角色
     */
    private List<Long> roleIdList;
}

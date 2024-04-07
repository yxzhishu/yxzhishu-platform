package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName UpdateAccountParam
 * @description: 修改账户信息入参
 * @author: 王朔
 * @create: 2023-11-26 12:48
 * @Version 1.0
 **/
@Data
public class UpdateAccountParam {
    /**
     * 账号ID
     */
    @NotNull(message ="账号ID不能为空")
    private Long accountId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 账号状态
     */
    private String status;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 其他信息
     */
    private String description;

    /**
     * 所属角色
     */
    private List<Long> roleIdList;
}

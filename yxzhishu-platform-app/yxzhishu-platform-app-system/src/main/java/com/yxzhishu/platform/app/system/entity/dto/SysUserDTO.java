package com.yxzhishu.platform.app.system.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/28 10:29
 * @remark:
 */
@Data
public class SysUserDTO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 出生日期
     */
    private Date birthDate;
    /**
     * 说明
     */
    private String description;
    /**
     * 省份ID
     */
    private Long provinceId;
    /**
     * 城市id
     */
    private Long cityId;
    /**
     * 城镇id
     */
    private Long townId;
    /**
     * 地址
     */
    private String address;
    /**
     * 省份
     */
    private Long province;
    /**
     * 城市
     */
    private Long city;
    /**
     * 城镇
     */
    private Long town;

}

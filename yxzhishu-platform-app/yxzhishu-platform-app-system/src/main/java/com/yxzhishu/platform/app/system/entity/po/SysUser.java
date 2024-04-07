package com.yxzhishu.platform.app.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/28 10:23
 * @remark:
 */
@Data
public class SysUser {
    /**
     * 用户ID
     */
    @TableId
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

}

package com.yxzhishu.platform.app.system.entity.dto;

import lombok.Data;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/5 9:29
 * @remark:
 */
@Data
public class SysMenuDTO{
    /**
     * 菜单id
     */
    private Long menuId;
    /**
     * 菜单名称(权限字符)
     */
    private String menuName;
    /**
     * 菜单类型（M目录 C菜单 F权限）'
     */
    private String menuType;
    /**
     * 路径及外链
     */
    private String menuPath;
    /**
     * 组件
     */
    private String component;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否隐藏菜单
     */
    private String hidden;
    /**
     * 系统标识
     */
    private String systemFlag;
    /**
     * 是否缓存
     */
    private String keepAlive;
    /**
     * 排序
     */
    private Integer sortOrder;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String description;
    /**
     * 角色id
     */
    private Long roleId;
}

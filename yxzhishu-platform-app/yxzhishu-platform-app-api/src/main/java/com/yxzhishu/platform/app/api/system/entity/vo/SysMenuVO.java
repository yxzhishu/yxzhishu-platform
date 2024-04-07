package com.yxzhishu.platform.app.api.system.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/29 10:37
 * @remark:
 */
@Data
public class SysMenuVO {
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
     * 子节点
     */
    private List<SysMenuVO> children;
}

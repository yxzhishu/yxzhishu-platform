package com.yxzhishu.platform.app.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @classDesc: 菜单表
 * @author: 王朔
 * @date: 2023/12/27 10:14
 * @remark:
 */
@Data
@TableName("sys.menu")
public class SysMenu {
    /**
     * 菜单id
     */
    @TableId
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
}

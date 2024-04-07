package com.yxzhishu.platform.app.api.system.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @classDesc: 路由展示
 * @author: 王朔
 * @date: 2023/12/29 10:55
 * @remark:
 */
@Data
public class RouteVO {
    /**
     * 菜单ID
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 路由
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 附属信息
     */
    private RouteMetaVO meta;
    /**
     * 自菜单
     */
    private List<RouteVO> children;

}

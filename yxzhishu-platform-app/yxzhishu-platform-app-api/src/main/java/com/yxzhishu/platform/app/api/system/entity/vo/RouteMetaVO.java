package com.yxzhishu.platform.app.api.system.entity.vo;

import lombok.Data;

/**
 * @classDesc: 路由附属信息
 * @author: 王朔
 * @date: 2023/12/29 10:57
 * @remark:
 */
@Data
public class RouteMetaVO {
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否隐藏
     */
    private String hidden;
    /**
     * 是否缓存
     */
    private String keepAlive;
}

package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @classDesc: 分页检索传输
 * @author: 王朔
 * @date: 2023/8/25 9:40
 * @remark:
 */
@Data
public class PageSearchParam {
    /**
     * 检索内容
     */
    private String search;
    /**
     * 当前页数
     */
    @NotNull(message = "页数不能为空")
    private Integer current;
    /**
     * 每页大小
     */
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;
    /**
     * 状态
     */
    private String status;
}

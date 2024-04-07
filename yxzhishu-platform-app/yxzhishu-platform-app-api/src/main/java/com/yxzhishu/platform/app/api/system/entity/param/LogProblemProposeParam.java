package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @classDesc: 提出问题
 * @author: 王朔
 * @date: 2024/3/15 11:47
 * @remark:
 */
@Data
public class LogProblemProposeParam {
    @NotNull(message = "标题不能为空")
    private String problemTitle;
    @NotNull(message = "问题内容不能为空")
    private String problemValue;
}

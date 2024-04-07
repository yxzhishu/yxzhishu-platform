package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:53
 * @remark:
 */
@Data
public class LogProblemAnswerParam {

    @NotNull(message = "ID不能为空")
    private Long problemId;

    @NotEmpty(message = "回答不能为空")
    private String answerValue;
}

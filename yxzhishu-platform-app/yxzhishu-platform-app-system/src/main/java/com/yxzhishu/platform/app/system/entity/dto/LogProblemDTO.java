package com.yxzhishu.platform.app.system.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:11
 * @remark:
 */
@Data
public class LogProblemDTO {
    /**
     * 问题记录ID
     */
    private Long problemId;
    /**
     * 问题标题
     */
    private String problemTitle;
    /**
     * 问题详情
     */
    private String problemValue;
    /**
     * 解答
     */
    private String answerValue;
    /**
     * 创建者ID
     */
    private Long createrId;
    /**
     * 创建者
     */
    private String createrName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 回答者ID
     */
    private Long answerId;
    /**
     * 回答者
     */
    private String answerName;
    /**
     * 回答时间
     */
    private Date answerTime;
}

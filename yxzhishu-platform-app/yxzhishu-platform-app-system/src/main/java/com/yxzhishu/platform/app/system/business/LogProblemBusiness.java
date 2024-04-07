package com.yxzhishu.platform.app.system.business;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemAnswerParam;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemProposeParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.vo.LogProblemVO;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:56
 * @remark:
 */
public interface LogProblemBusiness {

    void problemDelete(Long id);

    /**
     * 提出问题
     * @param param
     * @return
     */
    void problemPropose(LogProblemProposeParam param);

    /**
     * 回答问题
     * @param param
     * @return
     */
    void problemAnswer(LogProblemAnswerParam param);

    /**
     * 分页查询信息
     * @param param
     * @return
     */
    PageInfo<LogProblemVO> page(PageSearchParam param);
}

package com.yxzhishu.platform.app.system.controller;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.LogProblemApi;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemAnswerParam;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemProposeParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.vo.LogProblemVO;
import com.yxzhishu.platform.app.base.response.R;
import com.yxzhishu.platform.app.system.business.LogProblemBusiness;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:40
 * @remark:
 */
@RestController
public class LogProblemController implements LogProblemApi {
    @Resource
    LogProblemBusiness logProblemBusiness;

    @Override
    public R<String> problemDelete(Long id) {
        logProblemBusiness.problemDelete(id);
        return R.success("成功");
    }

    @Override
    public R<String> problemPropose(LogProblemProposeParam param) {
        logProblemBusiness.problemPropose(param);
        return R.success("成功");
    }

    @Override
    public R<String> problemAnswer(LogProblemAnswerParam param) {
        logProblemBusiness.problemAnswer(param);
        return R.success("成功");
    }

    @Override
    public R<PageInfo<LogProblemVO>> problemPage(PageSearchParam param) {
        PageInfo<LogProblemVO> pageInfo = logProblemBusiness.page(param);
        return R.success(pageInfo);
    }
}

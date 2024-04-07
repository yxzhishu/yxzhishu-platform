package com.yxzhishu.platform.app.system.business.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemAnswerParam;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemProposeParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.vo.LogProblemVO;
import com.yxzhishu.platform.app.system.business.LogProblemBusiness;
import com.yxzhishu.platform.app.system.comm.CommonHandle;
import com.yxzhishu.platform.app.system.entity.convert.LogProblemConvert;
import com.yxzhishu.platform.app.system.entity.dto.LogProblemDTO;
import com.yxzhishu.platform.app.system.entity.po.LogProblem;
import com.yxzhishu.platform.app.system.service.LogProblemService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:57
 * @remark:
 */
@Component
public class LogProblemBusinessImpl implements LogProblemBusiness {
    @Resource
    LogProblemService logProblemService;
    @Override
    public void problemDelete(Long id) {
        logProblemService.delete(id);
    }

    @Override
    public void problemPropose(LogProblemProposeParam param) {
        LogProblem logProblem = LogProblemConvert.INSTANCT.proposeParamToPo(param);
        logProblem.setCreaterId(StpUtil.getLoginIdAsLong());
        logProblemService.add(logProblem);
    }

    @Override
    public void problemAnswer(LogProblemAnswerParam param) {
        LogProblem logProblem = LogProblemConvert.INSTANCT.answerParamToPo(param);
        logProblem.setAnswerTime(new Date());
        logProblem.setAnswerId(StpUtil.getLoginIdAsLong());
        logProblemService.update(logProblem);
    }

    @Override
    public PageInfo<LogProblemVO> page(PageSearchParam param) {
        PageInfo<LogProblemDTO> pageInfo = logProblemService.page(param);
        List<LogProblemVO> voList = LogProblemConvert.INSTANCT.dtoToVo(pageInfo.getList());

        PageInfo<LogProblemVO> rePage = new PageInfo<>(voList);
        CommonHandle.convertPage(rePage,pageInfo);
        return rePage;
    }
}

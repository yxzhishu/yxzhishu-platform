package com.yxzhishu.platform.app.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.base.enums.ErrorCodeEnum;
import com.yxzhishu.platform.app.base.exception.YxException;
import com.yxzhishu.platform.app.system.entity.dto.LogProblemDTO;
import com.yxzhishu.platform.app.system.entity.po.LogProblem;
import com.yxzhishu.platform.app.system.mapper.LogProblemMapper;
import com.yxzhishu.platform.app.system.service.LogProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:39
 * @remark:
 */
@Service
@Slf4j
public class LogProblemServiceImpl implements LogProblemService {
    @Resource
    LogProblemMapper logProblemMapper;
    @Override
    public PageInfo<LogProblemDTO> page(PageSearchParam param) {
        Page<LogProblemDTO> page= PageHelper.startPage(param.getCurrent(), param.getPageSize());
        List<LogProblemDTO> list = logProblemMapper.findLogProblem(param);

        return page.toPageInfo();
    }

    @Override
    public int delete(Long problemId) {
        return logProblemMapper.deleteById(problemId);
    }

    @Override
    public int add(LogProblem logProblem) {
        return logProblemMapper.insert(logProblem);
    }

    @Override
    public int update(LogProblem logProblem) {
        if (ObjectUtils.isEmpty(logProblem.getProblemId())){
            throw new YxException(ErrorCodeEnum.SERVER_PARAM_ERROR);
        }
        return logProblemMapper.updateById(logProblem);
    }
}

package com.yxzhishu.platform.app.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.system.entity.dto.LogProblemDTO;
import com.yxzhishu.platform.app.system.entity.po.LogProblem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:14
 * @remark:
 */
@Mapper
public interface LogProblemMapper extends BaseMapper<LogProblem> {

    List<LogProblemDTO> findLogProblem(PageSearchParam param);

}

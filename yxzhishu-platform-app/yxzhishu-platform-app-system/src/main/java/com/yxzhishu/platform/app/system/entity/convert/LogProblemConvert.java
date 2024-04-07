package com.yxzhishu.platform.app.system.entity.convert;

import com.yxzhishu.platform.app.api.system.entity.param.LogProblemAnswerParam;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemProposeParam;
import com.yxzhishu.platform.app.api.system.entity.vo.LogProblemVO;
import com.yxzhishu.platform.app.system.entity.dto.LogProblemDTO;
import com.yxzhishu.platform.app.system.entity.po.LogProblem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:13
 * @remark:
 */
@Mapper
public interface LogProblemConvert {

    LogProblemConvert INSTANCT = Mappers.getMapper(LogProblemConvert.class);

    LogProblem proposeParamToPo(LogProblemProposeParam param);

    LogProblem answerParamToPo(LogProblemAnswerParam param);

    List<LogProblemVO> dtoToVo(List<LogProblemDTO> dto);
}

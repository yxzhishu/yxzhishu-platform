package com.yxzhishu.platform.app.api.system;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemAnswerParam;
import com.yxzhishu.platform.app.api.system.entity.param.LogProblemProposeParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.vo.LogProblemVO;
import com.yxzhishu.platform.app.base.response.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:41
 * @remark:
 */
@RequestMapping("/system/log")
public interface LogProblemApi {
    /**
     * 删除问题记录
     * @return
     */
    @GetMapping("/problemDelete")
    R<String> problemDelete(@RequestParam("id") Long id);

    /**
     * 提出问题
     * @param param
     * @return
     */
    @PostMapping("/problemPropose")
    R<String> problemPropose(@RequestBody @Validated LogProblemProposeParam param);

    /**
     * 回答问题
     * @param param
     * @return
     */
    @PostMapping("/problemAnswer")
    R<String> problemAnswer(@RequestBody @Validated LogProblemAnswerParam param);

    /**
     * 分页查询信息
     * @param param
     * @return
     */
    @PostMapping("/problemPage")
    R<PageInfo<LogProblemVO>> problemPage(@RequestBody @Validated PageSearchParam param);

}

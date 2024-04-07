package com.yxzhishu.platform.app.system.service;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.system.entity.dto.LogProblemDTO;
import com.yxzhishu.platform.app.system.entity.po.LogProblem;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/15 11:39
 * @remark:
 */
public interface LogProblemService {

    /**
     * 分页查询信息
     * @param param
     * @return
     */
    PageInfo<LogProblemDTO> page(PageSearchParam param);

    /**
     * 删除信息
     * @param problemId
     * @return
     */
    int delete(Long problemId);

    /**
     * 添加角色
     * @param logProblem
     */
    int add(LogProblem logProblem);

    /**
     * 修改角色信息
     * @param logProblem
     * @return
     */
    int update(LogProblem logProblem);
}

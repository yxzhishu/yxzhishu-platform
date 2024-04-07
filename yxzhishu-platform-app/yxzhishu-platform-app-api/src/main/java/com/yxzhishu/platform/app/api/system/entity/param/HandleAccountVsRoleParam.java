package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/29 13:30
 * @remark:
 */
@Data
public class HandleAccountVsRoleParam {
    @NotNull(message = "角色ID集合不能为空")
    private List<Long> roleIdList;
    @NotNull(message = "用户ID不能为空")
    private Long accountId;
    @NotNull(message = "处理标志不能为空")
    private Integer handleFlag;
}

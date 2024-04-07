package com.yxzhishu.platform.app.api.system.entity.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/3/14 16:34
 * @remark:
 */
@Data
public class HandleRoleVsMenuParam {
    @NotNull(message = "菜单ID集合不能为空")
    private List<Long> menuIdList;
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
}

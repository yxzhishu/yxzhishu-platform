package com.yxzhishu.platform.app.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @classDesc: 创建角色对应权限表
 * @author: 王朔
 * @date: 2023/12/27 10:14
 * @remark:
 */
@Data
@TableName("sys.role_vs_menu")
public class SysRoleVsMenu {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
}

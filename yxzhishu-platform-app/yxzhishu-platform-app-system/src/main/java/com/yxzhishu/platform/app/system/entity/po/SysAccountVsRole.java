package com.yxzhishu.platform.app.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @classDesc: 账号角色对应表
 * @author: 王朔
 * @date: 2023/12/27 10:12
 * @remark:
 */
@Data
@TableName("sys.account_vs_role")
public class SysAccountVsRole {
    /**
     * 账号ID
     */
    private Long accountId;
    /**
     * 角色ID
     */
    private Long roleId;
}

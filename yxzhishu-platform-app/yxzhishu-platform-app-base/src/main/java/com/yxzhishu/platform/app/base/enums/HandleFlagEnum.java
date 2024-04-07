package com.yxzhishu.platform.app.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @classDesc: 处理标志枚举
 * @author: 王朔
 * @date: 2023/12/29 13:39
 * @remark:
 */
@Getter
@AllArgsConstructor
public enum HandleFlagEnum {

    HANDLE_ADD(1,"添加数据处理"),

    HANDLE_REMOVE(0,"清除数据处理"),





    ;

    private Integer code;

    private String name;

}

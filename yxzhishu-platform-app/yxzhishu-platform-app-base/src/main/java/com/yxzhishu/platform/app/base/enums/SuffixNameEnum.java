package com.yxzhishu.platform.app.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @classDesc: 扩展名枚举
 * @author: 王朔
 * @date: 2023/11/9 10:24
 * @remark:
 */
@Getter
@AllArgsConstructor
public enum SuffixNameEnum {

    ZIP(".zip"),
    RAR(".rar"),
    TXT(".txt"),
    XLSX(".xlsx"),
    XLS(".xls"),
    ;
    private String code;
}

package com.yxzhishu.platform.app.base.utils;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/11/9 10:49
 * @remark:
 */
public class IntegerUtils {
    /**
     * 判断是否为2的倍数
     * @param number
     * @return
     */
    public static boolean isPowerOf2(Integer number){
        return (number&number-1)==0;
    }
}

package com.yxzhishu.platform.app.base.utils;

import java.util.regex.Pattern;

/**
 * @classDesc: 字符串工具类
 * @author: 王朔
 * @date: 2023/11/9 10:30
 * @remark:
 */
public class StrUtils {
    /**
     * 字符串组中是否存在null或者空串
     * @param str
     * @return
     */
    public static boolean hasNullOrEmpty(String ...str){
        for (String tmp:str){
            if (tmp==null||tmp.trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * 传入字符串是否均为纯数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        if(null == str){
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    /**
     * 字符串为null或空则返回outStr。
     * @param str
     * @param outStr
     * @return
     */
    public static String orElse(String str,String outStr){
        if (str==null||str.trim().isEmpty()){
            return outStr;
        }
        return str;
    }
}

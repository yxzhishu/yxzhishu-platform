package com.yxzhishu.platform.app.base.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/11/9 10:50
 * @remark:
 */
public class ListUtils {
    /**
     * object转list
     * @param obj
     * @return
     */
    public static List<Object> objToList(Object obj) {
        List<Object> list = new ArrayList<Object>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                list.add(o);
            }
            return list;
        }
        return null;
    }
}

package com.yxzhishu.platform.app.system.comm;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/11 9:23
 * @remark:
 */
public class CommonHandle {
    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD ="123123";
    /**
     * 分页转换
     * @param newPage
     * @param oldPage
     */
    public static void convertPage(PageInfo<?> newPage, PageInfo<?> oldPage){
        if (ObjectUtils.isEmpty(oldPage)){
            return;
        }
        newPage.setPages(oldPage.getPages());
        newPage.setPageNum(oldPage.getPageNum());
        newPage.setPageSize(oldPage.getPageSize());
        newPage.setTotal(oldPage.getTotal());
    }
    /**
     * 获取加密码
     * @param password
     * @param salt
     * @return
     */
    public static String getEncryptPassword(String password,String salt) {
        return SaSecureUtil.md5BySalt(password,salt);
    }
    /**
     * 获取盐值
     * @return
     */
    public static String getSalt(){
        return UUID.randomUUID().toString().replace("-","").substring(16);
    }
}

package com.yxzhishu.platform.app.start.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/27 11:45
 * @remark:
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能,定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handle -> {
                    //全部校验是否登录
                    SaRouter.match("/**").check(r -> StpUtil.checkLogin());
                    //无特殊情况不设置查询鉴权(list用来单纯显示页面)
                    //账号
                    SaRouter.match("/system/account/register")
                            .check(r -> StpUtil.checkPermission("sys.account.add"));
                    SaRouter.match("/system/account/resetPassword","/system/account/update")
                            .check(r -> StpUtil.checkPermission("sys.account.edit"));
                    //角色
                    SaRouter.match("/system/role/add")
                            .check(r -> StpUtil.checkPermission("sys.role.add"));
                    SaRouter.match("/system/role/update","/system/role/handleAuthorization")
                            .check(r -> StpUtil.checkPermission("sys.role.edit"));
                    SaRouter.match("/system/role/delete")
                            .check(r -> StpUtil.checkPermission("sys.role.delete"));
                    //问题单
                    SaRouter.match("/system/log/problemDelete")
                            .check(r -> StpUtil.checkPermission("log.problemLog.delete"));
                    SaRouter.match("/system/log/problemPropose","/system/log/problemAnswer")
                            .check(r -> StpUtil.checkPermission("log.problemLog.edit"));




//                    SaRouter.match("/system/account/**")
//                            .notMatch("/system/account/updatePassword","/system/account/update","/system/account/switchRoles")
//                            .check(r -> StpUtil.checkPermission("sys.account.list"));
                }))
                .addPathPatterns("/**")
                //排除路由拦截
                .excludePathPatterns("/system/account/doLogin","/system/account/doLogout", "/open/**");
    }
}

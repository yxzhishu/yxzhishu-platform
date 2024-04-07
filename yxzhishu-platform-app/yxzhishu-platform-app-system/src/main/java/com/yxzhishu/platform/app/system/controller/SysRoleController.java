package com.yxzhishu.platform.app.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.SysRoleApi;
import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageRoleByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleVsMenuParam;
import com.yxzhishu.platform.app.api.system.entity.vo.SysRoleVO;
import com.yxzhishu.platform.app.base.response.R;
import com.yxzhishu.platform.app.system.business.SysRoleBusiness;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/25 17:07
 * @remark:
 */
@SaCheckLogin
@RestController
public class SysRoleController implements SysRoleApi {

    @Resource
    SysRoleBusiness sysRoleBusiness;

    /**
     * 分页查询角色
     *
     * @param param
     * @return
     */
    @Override
    public R<PageInfo<SysRoleVO>> page(PageSearchParam param) {
        PageInfo<SysRoleVO> pageInfo = sysRoleBusiness.page(param);
        return R.success(pageInfo);
    }

    /**
     * 添加角色
     *
     * @param param
     * @return
     */
    @Override
    public R<String> add(HandleRoleParam param) {
        sysRoleBusiness.add(param);
        return R.success("新增成功");
    }

    /**
     * 修改角色信息
     *
     * @param param
     * @return
     */
    @Override
    public R<String> update(HandleRoleParam param) {
        sysRoleBusiness.update(param);
        return R.success("修改成功");
    }

    /**
     * 删除角色信息
     *
     * @param roleId
     * @return
     */
    @Override
    public R<String> delete(Long roleId) {
        sysRoleBusiness.delete(roleId);
        return R.success("删除成功");
    }

    @Override
    public R<List<SysRoleVO>> findByRoleId(List<Long> roleIds) {
        List<SysRoleVO> list = sysRoleBusiness.findByRoleId(roleIds);
        return R.success(list);
    }

    @Override
    public R<List<SysRoleVO>> findByAccurate(PageRoleByAccurateParam param) {
        List<SysRoleVO> list = sysRoleBusiness.findByAccurate(param);
        return R.success(list);
    }

    @Override
    public R<List<SysRoleVO>> findNormalByAccurate(PageRoleByAccurateParam param) {
        List<SysRoleVO> list = sysRoleBusiness.findNormalByAccurate(param);
        return R.success(list);
    }

    @Override
    public R<String> handleAuthorization(HandleRoleVsMenuParam param) {
        sysRoleBusiness.handleAuthorization(param);
        return R.success("完成");
    }

}

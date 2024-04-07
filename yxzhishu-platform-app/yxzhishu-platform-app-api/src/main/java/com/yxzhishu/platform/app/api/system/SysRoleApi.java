package com.yxzhishu.platform.app.api.system;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageRoleByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleVsMenuParam;
import com.yxzhishu.platform.app.api.system.entity.vo.SysRoleVO;
import com.yxzhishu.platform.app.base.response.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @classDesc: 角色权限
 * @author: 王朔
 * @date: 2023/11/28 13:45
 * @remark:
 */
@RequestMapping("/system/role")
public interface SysRoleApi {
    /**
     * 分页查询角色
     * @param param
     * @return
     */
    @PostMapping("/page")
    R<PageInfo<SysRoleVO>> page(@RequestBody PageSearchParam param);

    /**
     * 添加角色
     * @param param
     * @return
     */
    @PostMapping("/add")
    R<String> add(@RequestBody HandleRoleParam param);

    /**
     * 修改角色信息
     * @param param
     * @return
     */
    @PostMapping("/update")
    R<String> update(@RequestBody HandleRoleParam param);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    @GetMapping("/delete")
    R<String> delete(@RequestParam("roleId")Long roleId);

    /**
     * 根据角色id获取角色信息
     * @param roleIds
     * @return
     */
    @GetMapping("/findByRoleId")
    R<List<SysRoleVO>> findByRoleId(@RequestParam("roleIds")List<Long> roleIds);

    /**
     * 精确查询
     * @param param
     * @return
     */
    @PostMapping("/findByAccurate")
    R<List<SysRoleVO>> findByAccurate(@RequestBody PageRoleByAccurateParam param);

    /**
     * 精确查询
     * @param param
     * @return
     */
    @PostMapping("/findNormalByAccurate")
    R<List<SysRoleVO>> findNormalByAccurate(@RequestBody PageRoleByAccurateParam param);

    /**
     * 处理授权
     * @param param
     * @return
     */
    @PostMapping("/handleAuthorization")
    R<String> handleAuthorization(@RequestBody  @Validated HandleRoleVsMenuParam param);
}

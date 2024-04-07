package com.yxzhishu.platform.app.system.business.impl;

import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleParam;
import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleVsMenuParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageRoleByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.vo.SysRoleVO;
import com.yxzhishu.platform.app.base.constant.CommonConst;
import com.yxzhishu.platform.app.base.enums.ErrorCodeEnum;
import com.yxzhishu.platform.app.base.exception.YxException;
import com.yxzhishu.platform.app.system.business.SysRoleBusiness;
import com.yxzhishu.platform.app.system.comm.CommonHandle;
import com.yxzhishu.platform.app.system.entity.convert.SysRoleConvert;
import com.yxzhishu.platform.app.system.entity.dto.SysRoleDTO;
import com.yxzhishu.platform.app.system.entity.po.SysAccountVsRole;
import com.yxzhishu.platform.app.system.entity.po.SysRole;
import com.yxzhishu.platform.app.system.service.SysAccountService;
import com.yxzhishu.platform.app.system.service.SysAccountVsRoleService;
import com.yxzhishu.platform.app.system.service.SysRoleService;
import com.yxzhishu.platform.app.system.service.SysRoleVsMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/15 17:10
 * @remark:
 */
@Component
@Slf4j
public class SysRoleBusinessImpl implements SysRoleBusiness {
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysAccountService sysAccountService;
    @Resource
    SysRoleVsMenuService sysRoleVsMenuService;
    @Resource
    SysAccountVsRoleService sysAccountVsRoleService;

    @Override
    public PageInfo<SysRoleVO> page(PageSearchParam param) {
        PageInfo<SysRoleDTO> pageInfo = sysRoleService.page(param);
        List<SysRoleVO> voList = SysRoleConvert.INSTANCT.dtoToVo(pageInfo.getList());

        PageInfo<SysRoleVO> rePage = new PageInfo<>(voList);
        CommonHandle.convertPage(rePage,pageInfo);

        return rePage;
    }

    @Override
    public void add(HandleRoleParam param) {
        SysRole role = SysRoleConvert.INSTANCT.handleRoleToPo(param);
        sysRoleService.add(role);
    }

    @Override
    public void update(HandleRoleParam param) {
        SysRole role = SysRoleConvert.INSTANCT.handleRoleToPo(param);
        //查询待修改信息
        SysRoleDTO roleOld = getByRoleId(role.getRoleId());
        if (CommonConst.STR_ONE.equals(roleOld.getSystemFlag())){
            throw new YxException(ErrorCodeEnum.CLIENT_ROLE_ERROR,"系统级不允许修改!");
        }
        sysRoleService.update(role);
    }

    @Override
    public void delete(Long roleId) {
        PageRoleByAccurateParam param = new PageRoleByAccurateParam();
        param.setRoleId(roleId);
        List<SysRoleDTO> list = sysRoleService.findByAccurate(param);
        if (CollectionUtils.isEmpty(list)){
            throw new YxException(ErrorCodeEnum.SERVER_DATA_ERROR,"不存在对应数据!");
        }
        if (CommonConst.STR_ONE.equals(list.get(0).getSystemFlag())){
            throw new YxException(ErrorCodeEnum.CLIENT_ROLE_ERROR,"系统级不允许修改!");
        }

        List<SysAccountVsRole> accountList = sysAccountVsRoleService.findData(null,roleId);
        if (!CollectionUtils.isEmpty(accountList)){
            throw new YxException(ErrorCodeEnum.CLIENT_ASSOCIATED_DATA,"请检查该角色下是否存在账号信息");
        }

        sysRoleService.delete(roleId);
    }

    @Override
    public List<SysRoleVO> findByRoleId(List<Long> roleIdList) {
        List<SysRoleDTO> list = sysRoleService.findByRoleId(roleIdList);
        List<SysRoleVO> voList = SysRoleConvert.INSTANCT.dtoToVo(list);
        return voList;
    }

    @Override
    public List<SysRoleVO> findByAccurate(PageRoleByAccurateParam param) {
        List<SysRoleDTO> list = sysRoleService.findByAccurate(param);
        return SysRoleConvert.INSTANCT.dtoToVo(list);
    }

    @Override
    public List<SysRoleVO> findNormalByAccurate(PageRoleByAccurateParam param) {
        param.setStatus("0");
        List<SysRoleDTO> list = sysRoleService.findByAccurate(param);

        list.forEach(item->item.setAccountId(null));
        list = list.stream().distinct().collect(Collectors.toList());
//        list = list.stream().filter(item->item.getRoleId()!=CommonConst.INT_ZERO).collect(Collectors.toList());
        return SysRoleConvert.INSTANCT.dtoToVo(list);
    }

    @Override
    public void handleAuthorization(HandleRoleVsMenuParam param) {
        if (CommonConst.LONG_ZERO.equals(param.getRoleId())){
            throw new YxException(ErrorCodeEnum.CLIENT_ROLE_ERROR,"系统级不允许修改!");
        }
        sysRoleVsMenuService.updateVsInfo(param.getRoleId(),param.getMenuIdList());
    }

    /**
     * 根据roleId获取角色
     * @param roleId
     * @return
     */
    private SysRoleDTO getByRoleId(Long roleId){
        PageRoleByAccurateParam accurateParam = new PageRoleByAccurateParam();
        accurateParam.setRoleId(roleId);
        Optional<SysRoleDTO> optional = sysRoleService.findByAccurate(accurateParam).stream().findFirst();
        if (!optional.isPresent()){
            throw new YxException(ErrorCodeEnum.SERVER_DATA_ERROR,"不存在对应数据!");
        }
        return optional.get();
    }
}

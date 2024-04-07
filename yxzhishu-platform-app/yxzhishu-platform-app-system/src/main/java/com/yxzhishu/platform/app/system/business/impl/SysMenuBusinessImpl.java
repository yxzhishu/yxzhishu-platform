package com.yxzhishu.platform.app.system.business.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageMenuByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.api.system.entity.vo.RouteMetaVO;
import com.yxzhishu.platform.app.api.system.entity.vo.RouteVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysMenuVO;
import com.yxzhishu.platform.app.base.constant.CommonConst;
import com.yxzhishu.platform.app.system.business.SysMenuBusiness;
import com.yxzhishu.platform.app.system.comm.CommonHandle;
import com.yxzhishu.platform.app.system.entity.convert.SysMenuConvert;
import com.yxzhishu.platform.app.system.entity.dto.SysMenuDTO;
import com.yxzhishu.platform.app.system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/1/15 13:53
 * @remark:
 */
@Component
@Slf4j
public class SysMenuBusinessImpl implements SysMenuBusiness {
    @Resource
    SysMenuService sysMenuService;

    @Override
    public List<SysMenuVO> findMenu() {
        List<SysMenuDTO> list = sysMenuService.findMenu();
        return SysMenuConvert.INSTANCT.dtoToVO(list);
    }

    @Override
    public List<RouteVO> findRoute() {
        List<String> roleList = StpUtil.getRoleList();
        List<Long> longList = roleList.stream().map(Long::valueOf).collect(Collectors.toList());

        PageMenuByAccurateParam param = new PageMenuByAccurateParam();
        if (!longList.contains(CommonConst.LONG_ZERO)){
            param.setRoleIdList(longList);
        }
        List<SysMenuDTO> list = sysMenuService.findByAccurate(param);
        //查询父级目录
        List<SysMenuDTO> patientList = list.stream().filter(item->!item.getParentId().equals(CommonConst.LONG_MINUS_ONE)).collect(Collectors.toList());
        List<SysMenuDTO> finalList = new ArrayList<>();
        patientList.forEach(item->{
            finalList.addAll(findParentMenu(item));
        });
        list.addAll(finalList);
        //去重排序
        list.forEach(item->item.setRoleId(null));
        list = list.stream().distinct()
                .sorted(Comparator.comparing(SysMenuDTO::getSortOrder))
                .collect(Collectors.toList());
        //序列化
        return serializeRoute(list,-1L);
    }

    @Override
    public List<Long> findMenuIdByRoleId(Long roleId) {
        if (ObjectUtils.isEmpty(roleId)){
            roleId = Long.parseLong(StpUtil.getRoleList().get(0));
        }
        PageMenuByAccurateParam param = new PageMenuByAccurateParam();
        if(!CommonConst.LONG_ZERO.equals(roleId)){
            param.setRoleId(roleId);
        }
        List<SysMenuDTO> menuList = sysMenuService.findByAccurate(param);

        List<Long> list = menuList.stream().map(menu->menu.getMenuId()).collect(Collectors.toList());

        return list;
    }


    /**
     * 序列化路由
     * @return
     */
    private List<RouteVO> serializeRoute(List<SysMenuDTO> lsMenu, Long parentId){
        List<RouteVO> list = new ArrayList<>();
        lsMenu = lsMenu.stream().filter(menu-> !"F".equals(menu.getMenuType())).collect(Collectors.toList());
        for (SysMenuDTO menu : lsMenu) {
            if (!parentId.equals(menu.getParentId())){
                continue;
            }
            RouteVO sysRouteVO= SysMenuConvert.INSTANCT.dtoToRouteVo(menu);
            RouteMetaVO sysMetaVO= SysMenuConvert.INSTANCT.dtoToRouteMetaVO(menu);
            sysRouteVO.setMeta(sysMetaVO);
            if ("M".equals(menu.getMenuType())){
                List<RouteVO> children = serializeRoute(lsMenu,menu.getMenuId());
                if(children.size()>0){
                    sysRouteVO.setChildren(children);
                }
            }
            list.add(sysRouteVO);
        }
        return list;
    }

    @Override
    public PageInfo<SysMenuVO> page(PageSearchParam param) {
        PageInfo<SysMenuDTO> pageInfo = sysMenuService.page(param);
        List<SysMenuVO> voList = SysMenuConvert.INSTANCT.dtoToVO(pageInfo.getList());

        PageInfo<SysMenuVO> rePage = new PageInfo<>(voList);
        CommonHandle.convertPage(rePage,pageInfo);
        return rePage;
    }

    @Override
    public List<SysMenuVO> findMenuBySerialize() {
        List<SysMenuDTO> list = sysMenuService.findMenu();
        List<SysMenuVO> reList = serializeMenu(list,-1L);
        return reList;
    }

    /**
     * 序列化路由
     * @return
     */
    private List<SysMenuVO> serializeMenu(List<SysMenuDTO> lsMenu, Long parentId){
        List<SysMenuVO> list = new ArrayList<>();
        for (SysMenuDTO menu : lsMenu) {
            if (!parentId.equals(menu.getParentId())){
                continue;
            }
            SysMenuVO sysMenuVO= SysMenuConvert.INSTANCT.dtoToVO(menu);
            List<SysMenuVO> children = serializeMenu(lsMenu,menu.getMenuId());
            if(children.size()>0){
                sysMenuVO.setChildren(children);
            }
            list.add(sysMenuVO);
        }
        return list;
    }

    private List<SysMenuDTO> findParentMenu(SysMenuDTO menu){
        List<SysMenuDTO> reList = new ArrayList<>();
        if (menu != null && menu.getParentId() != -1) {
            PageMenuByAccurateParam param = new PageMenuByAccurateParam();
            param.setMenuIdList(Collections.singletonList(menu.getParentId()));
            List<SysMenuDTO> list = sysMenuService.findByAccurate(param);
            reList.addAll(list);
            List<SysMenuDTO> tmp = findParentMenu(list.get(0)); // 递归查找父级菜单
            reList.addAll(tmp);
        }
        return reList;
    }

}

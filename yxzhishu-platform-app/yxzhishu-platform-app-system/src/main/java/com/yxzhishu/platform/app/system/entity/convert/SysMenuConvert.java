package com.yxzhishu.platform.app.system.entity.convert;


import com.yxzhishu.platform.app.api.system.entity.vo.RouteMetaVO;
import com.yxzhishu.platform.app.api.system.entity.vo.RouteVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysMenuVO;
import com.yxzhishu.platform.app.system.entity.dto.SysMenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/29 11:09
 * @remark:
 */
@Mapper
public interface SysMenuConvert {

    SysMenuConvert INSTANCT = Mappers.getMapper(SysMenuConvert.class);

    @Mappings({
            @Mapping(source = "menuId",target = "id"),
            @Mapping(source = "menuName",target = "name"),
            @Mapping(source = "menuPath",target = "path"),
    })
    RouteVO dtoToRouteVo(SysMenuDTO dto);

    RouteMetaVO dtoToRouteMetaVO(SysMenuDTO po);

    List<SysMenuVO> dtoToVO(List<SysMenuDTO> dto);
    SysMenuVO dtoToVO(SysMenuDTO dto);


}

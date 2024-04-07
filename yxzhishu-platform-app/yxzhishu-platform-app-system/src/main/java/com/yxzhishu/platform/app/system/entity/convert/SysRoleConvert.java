package com.yxzhishu.platform.app.system.entity.convert;

import com.yxzhishu.platform.app.api.system.entity.param.HandleRoleParam;
import com.yxzhishu.platform.app.api.system.entity.vo.SysRoleVO;
import com.yxzhishu.platform.app.system.entity.dto.SysRoleDTO;
import com.yxzhishu.platform.app.system.entity.po.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/27 10:31
 * @remark:
 */
@Mapper
public interface SysRoleConvert {
    SysRoleConvert INSTANCT = Mappers.getMapper(SysRoleConvert.class);

    SysRole handleRoleToPo(HandleRoleParam param);

    List<SysRoleVO> dtoToVo(List<SysRoleDTO> dto);
}

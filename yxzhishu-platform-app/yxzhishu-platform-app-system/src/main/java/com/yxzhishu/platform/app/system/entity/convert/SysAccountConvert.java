package com.yxzhishu.platform.app.system.entity.convert;

import com.yxzhishu.platform.app.api.system.entity.param.RegisterParam;
import com.yxzhishu.platform.app.api.system.entity.param.UpdateAccountParam;
import com.yxzhishu.platform.app.api.system.entity.vo.SysAccountVO;
import com.yxzhishu.platform.app.system.entity.dto.SysAccountDTO;
import com.yxzhishu.platform.app.system.entity.po.SysAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysAccountConvert {
    SysAccountConvert INSTANCT = Mappers.getMapper(SysAccountConvert.class);

    SysAccountVO dtoToVo(SysAccountDTO dto);

    List<SysAccountVO> dtoToVo(List<SysAccountDTO> dto);

    SysAccount registerParamToPo(RegisterParam param);

    SysAccount updateAccountParamToPo(UpdateAccountParam param);


}

package com.yxzhishu.platform.app.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.PageAccountByAccurateParam;
import com.yxzhishu.platform.app.api.system.entity.param.PageSearchParam;
import com.yxzhishu.platform.app.base.enums.ErrorCodeEnum;
import com.yxzhishu.platform.app.base.exception.YxException;
import com.yxzhishu.platform.app.base.utils.StrUtils;
import com.yxzhishu.platform.app.system.comm.CommonHandle;
import com.yxzhishu.platform.app.system.entity.dto.SysAccountDTO;
import com.yxzhishu.platform.app.system.entity.po.SysAccount;
import com.yxzhishu.platform.app.system.mapper.SysAccountMapper;
import com.yxzhishu.platform.app.system.service.SysAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2024/2/18 10:17
 * @remark:
 */
@Service
@Slf4j
public class SysAccountServiceImpl implements SysAccountService {
    @Resource
    SysAccountMapper sysAccountMapper;

    @Override
    public int update(SysAccount account){
        if (ObjectUtils.isEmpty(account)){
            throw new YxException(ErrorCodeEnum.CLIENT_REQUEST_ERROR,"账号信息不能为空！");
        }
        if (ObjectUtils.isEmpty(account.getAccountId())){
            throw new YxException(ErrorCodeEnum.CLIENT_REQUEST_ERROR,"accountId不能为空");
        }
        return sysAccountMapper.updateById(account);
    }

    @Override
    public Long add(SysAccount account) {
        if (ObjectUtils.isEmpty(account)){
            throw new YxException(ErrorCodeEnum.CLIENT_REQUEST_ERROR,"账号信息不能为空！");
        }
        if (ObjectUtils.isEmpty(account.getAccountCode())){
            throw new YxException(ErrorCodeEnum.CLIENT_REQUEST_ERROR,"ACCOUNT_CODE不能为空!");
        }
        if (StrUtils.isNumber(account.getAccountCode())){
            throw new YxException(ErrorCodeEnum.CLIENT_REQUEST_ERROR,"ACCOUNT_CODE不能为纯数字!");
        }
        PageAccountByAccurateParam param = new PageAccountByAccurateParam();
        param.setAccountCode(account.getAccountCode());
        List<SysAccountDTO> list = sysAccountMapper.findByAccurate(param);
        if (!CollectionUtils.isEmpty(list)){
            throw new YxException(ErrorCodeEnum.SERVER_DATA_ERROR,"已存在对应数据!");
        }
        String salt =CommonHandle.getSalt();
        String psdStr = StrUtils.orElse(account.getPassword(),CommonHandle.DEFAULT_PASSWORD);
        String password =CommonHandle.getEncryptPassword(psdStr,salt);

        account.setSalt(salt);
        account.setPassword(password);
        account.setCreaterId(Optional.ofNullable(account.getCreaterId()).orElse(account.getAccountId()));
        account.setNickname(StrUtils.orElse(account.getNickname(),"未命名"));
        int i = sysAccountMapper.insert(account);
        if (1>i){
            throw new YxException(ErrorCodeEnum.SERVER_DATA_ERROR,"添加数据失败!");
        }
        return account.getAccountId();
    }

    @Override
    public PageInfo<SysAccountDTO> page(PageSearchParam param) {
        Page<SysAccountDTO> page= PageHelper.startPage(param.getCurrent(), param.getPageSize());
        List<SysAccountDTO> list = sysAccountMapper.findAccount(param);
        return page.toPageInfo();
    }

    @Override
    public PageInfo<SysAccountDTO> pageByAccurate(PageAccountByAccurateParam param) {
        Page<SysAccountDTO> page= PageHelper.startPage(param.getCurrent(), param.getPageSize());
        List<SysAccountDTO> list = sysAccountMapper.findByAccurate(param);
        return page.toPageInfo();
    }

    @Override
    public List<SysAccountDTO> findByAccurate(PageAccountByAccurateParam param) {
        List<SysAccountDTO> list = sysAccountMapper.findByAccurate(param);
        return list;
    }
}

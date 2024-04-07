package com.yxzhishu.platform.app.system.business.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import com.yxzhishu.platform.app.api.system.entity.param.*;
import com.yxzhishu.platform.app.api.system.entity.vo.DoLoginVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysAccountVO;
import com.yxzhishu.platform.app.api.system.entity.vo.SysRoleVO;
import com.yxzhishu.platform.app.base.constant.CommonConst;
import com.yxzhishu.platform.app.base.enums.ErrorCodeEnum;
import com.yxzhishu.platform.app.base.exception.YxException;
import com.yxzhishu.platform.app.base.utils.StrUtils;
import com.yxzhishu.platform.app.system.business.SysAccountBusiness;
import com.yxzhishu.platform.app.system.comm.CommonHandle;
import com.yxzhishu.platform.app.system.entity.convert.SysAccountConvert;
import com.yxzhishu.platform.app.system.entity.convert.SysRoleConvert;
import com.yxzhishu.platform.app.system.entity.dto.SysAccountDTO;
import com.yxzhishu.platform.app.system.entity.dto.SysRoleDTO;
import com.yxzhishu.platform.app.system.entity.po.SysAccount;
import com.yxzhishu.platform.app.system.service.SysAccountService;
import com.yxzhishu.platform.app.system.service.SysAccountVsRoleService;
import com.yxzhishu.platform.app.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/12/29 13:56
 * @remark:
 */
@Component
@Slf4j
public class SysAccountBusinessImpl implements SysAccountBusiness {
    @Resource
    SysAccountService sysAccountService;
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysAccountVsRoleService sysAccountVsRoleService;

    @Override
    public DoLoginVO doLogin(DoLoginParam param) {
        PageAccountByAccurateParam findParam = getAccurateParamByLoginParam(param);
        List<SysAccountDTO> list = sysAccountService.findByAccurate(findParam);
        if (CollectionUtils.isEmpty(list)) {
            throw new YxException(ErrorCodeEnum.CLIENT_ACCOUNT_ERROR,"账号密码错误!");
        }
        if (list.size() > 1) {
            throw new YxException(ErrorCodeEnum.SERVER_DATA_ERROR, "存在多条账号信息!");
        }
        SysAccountDTO account = list.get(0);
        // 判断密码
        if (!CommonHandle.getEncryptPassword(param.getPassword(), account.getSalt()).equals(account.getPassword())) {
            log.info("密码错误：输入密码为：{} ", param.getPassword());
            throw new YxException(ErrorCodeEnum.CLIENT_ACCOUNT_ERROR,"账号密码错误!");
        }
        // 判断账户状态
        if (!CommonConst.STR_ZERO.equals(account.getStatus())) {
            throw new YxException(ErrorCodeEnum.CLIENT_ACCOUNT_ERROR,"账号状态异常!");
        }
        //获取角色列表
        findRole(account);
        SysAccountVO sysAccountVO = SysAccountConvert.INSTANCT.dtoToVo(account);
        // 账户信息正确，登陆成功
        successLogin(account);
        // 登录vo
        DoLoginVO vo = new DoLoginVO();
        vo.setAccountInfo(sysAccountVO);
        vo.setTokenInfo(StpUtil.getTokenInfo());
        return vo;
    }

    @Override
    public void register(RegisterParam param) {
        //添加账户信息
        SysAccount account = SysAccountConvert.INSTANCT.registerParamToPo(param);
        Long accountId = sysAccountService.add(account);
        sysAccountVsRoleService.updateVsInfo(accountId, param.getRoleIdList());
    }

    @Override
    public void updatePassword(UpdatePasswrodParam param) {
        boolean isUpdate = CommonConst.INT_ONE == param.getUpdateFlag();

        Long accountId = isUpdate ? StpUtil.getLoginIdAsLong() : param.getAccountId();
        SysAccountDTO account = getByAccountId(accountId);

        if (isUpdate) {
            //修改密码-判断原密码
            if (ObjectUtils.isEmpty(param.getOldPassword())
                    || !account.getPassword().equals(CommonHandle.getEncryptPassword(param.getOldPassword(), account.getSalt()))) {
                throw new YxException(ErrorCodeEnum.CLIENT_ACCOUNT_ERROR,"账号密码错误!");
            }
        } else {
            //重置密码-判断是否系统用户
            if (CommonConst.STR_ONE.equals(account.getSystemFlag())) {
                throw new YxException(ErrorCodeEnum.CLIENT_ROLE_ERROR,"系统级不允许修改!");
            }
        }
        String psdStr = isUpdate ? param.getPassword() : CommonHandle.DEFAULT_PASSWORD;

        SysAccount updateAccount = new SysAccount();
        updateAccount.setAccountId(accountId);
        updateAccount.setSalt(CommonHandle.getSalt());
        updateAccount.setPassword(CommonHandle.getEncryptPassword(psdStr, updateAccount.getSalt()));
        sysAccountService.update(updateAccount);
    }

    @Override
    public void update(UpdateAccountParam param) {
        SysAccount account = SysAccountConvert.INSTANCT.updateAccountParamToPo(param);
        //查询待修改信息
        SysAccountDTO accountOld = getByAccountId(account.getAccountId());
        if (CommonConst.STR_ONE.equals(accountOld.getSystemFlag())) {
            throw new YxException(ErrorCodeEnum.CLIENT_ROLE_ERROR,"系统级不允许修改!");
        }
        //修改数据
        sysAccountService.update(account);
        //修改角色对应关系
        sysAccountVsRoleService.updateVsInfo(account.getAccountId(),param.getRoleIdList());
    }

    @Override
    public PageInfo<SysAccountVO> page(PageSearchParam param) {
        PageInfo<SysAccountDTO> page = sysAccountService.page(param);
        List<SysAccountVO> voList = SysAccountConvert.INSTANCT.dtoToVo(page.getList());
        //
        PageRoleByAccurateParam accurateParam = new PageRoleByAccurateParam();
        accurateParam.setAccountIdList(voList.stream().map(SysAccountVO::getAccountId).distinct().collect(Collectors.toList()));
        List<SysRoleDTO> roleList = sysRoleService.findByAccurate(accurateParam);
        if (!CollectionUtils.isEmpty(roleList)) {
            Map<Long, List<SysRoleDTO>> map = roleList.stream().collect(Collectors.groupingBy(SysRoleDTO::getAccountId));
            for (SysAccountVO vo : voList) {
                if (!map.containsKey(vo.getAccountId())) {
                    continue;
                }
                List<SysRoleVO> roleVOList = SysRoleConvert.INSTANCT.dtoToVo(map.get(vo.getAccountId()));
                vo.setRoleIdList(roleVOList.stream().map(item->item.getRoleId()).collect(Collectors.toList()));
            }
        }
        PageInfo<SysAccountVO> rePageInfo = new PageInfo<>(voList);
        CommonHandle.convertPage(rePageInfo, page);
        return rePageInfo;
    }

    @Override
    public PageInfo<SysAccountVO> pageByAccurate(PageAccountByAccurateParam param) {
        PageInfo<SysAccountDTO> page = sysAccountService.pageByAccurate(param);
        List<SysAccountVO> voList = SysAccountConvert.INSTANCT.dtoToVo(page.getList());
        PageInfo<SysAccountVO> rePageInfo = new PageInfo<>(voList);
        CommonHandle.convertPage(rePageInfo, page);
        return rePageInfo;
    }

    /**
     * 登陆成功处理
     * @param account
     */
    private void successLogin(SysAccountDTO account) {
        SysAccount updateAccount = new SysAccount();
        updateAccount.setAccountId(account.getAccountId());
        updateAccount.setLastLoginTime(new Date());
        sysAccountService.update(updateAccount);
        StpUtil.login(account.getAccountId());
    }

    /**
     * 根据登录参数获取精确查询参数
     *
     * @param param
     * @return
     */
    private PageAccountByAccurateParam getAccurateParamByLoginParam(DoLoginParam param) {
        PageAccountByAccurateParam findParam = new PageAccountByAccurateParam();
        if (StrUtils.isNumber(param.getAccountCode())) {
            findParam.setPhone(param.getAccountCode());
        } else if (param.getAccountCode().contains("@")) {
            findParam.setMail(param.getAccountCode());
        } else {
            findParam.setAccountCode(param.getAccountCode());
        }
        return findParam;
    }

    /**
     * 根据accountId获取账号
     *
     * @param accountId
     * @return
     */
    private SysAccountDTO getByAccountId(Long accountId) {
        PageAccountByAccurateParam accurateParam = new PageAccountByAccurateParam();
        accurateParam.setAccountId(accountId);
        Optional<SysAccountDTO> optional = sysAccountService.findByAccurate(accurateParam).stream().findFirst();
        if (!optional.isPresent()) {
            throw new YxException(ErrorCodeEnum.SERVER_DATA_ERROR,"不存在对应数据!");
        }
        return optional.get();
    }
    /**
     * 获取用户角色
     * @param account
     */
    private List<SysRoleDTO> findRole(SysAccountDTO account){
        PageRoleByAccurateParam roleParam = new PageRoleByAccurateParam();
        roleParam.setAccountId(account.getAccountId());
        List<SysRoleDTO> roleList = sysRoleService.findByAccurate(roleParam);
        //判断是否实际存在对应角色
        if (CollectionUtils.isEmpty(roleList)){
            throw new YxException(ErrorCodeEnum.SERVER_DATA_ERROR,"无对应角色!");
        }
        return roleList;
    }
}

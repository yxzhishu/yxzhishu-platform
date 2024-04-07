package com.yxzhishu.platform.app.base.exception;

import com.yxzhishu.platform.app.base.enums.ErrorCodeEnum;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/11/9 10:26
 * @remark:
 */
public class YxException extends RuntimeException{
    private ErrorCodeEnum errorCodeEnum;

    private String message;

    public YxException(String defaultMessage){
        this.message=defaultMessage;
        this.errorCodeEnum=ErrorCodeEnum.SERVER_ERROR;
    }

    public YxException(ErrorCodeEnum errorCodeEnum, String defaultMessage){
        this.errorCodeEnum=errorCodeEnum;
        this.message=defaultMessage;
    }

    public YxException(ErrorCodeEnum errorCodeEnum){
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getBaseErrorCodeEnum(){
        return this.errorCodeEnum;
    }
    @Override
    public String getMessage(){
        return this.message;
    }
}

package com.yxzhishu.platform.app.start.config;

import cn.dev33.satoken.exception.SaTokenException;
import com.yxzhishu.platform.app.base.enums.ErrorCodeEnum;
import com.yxzhishu.platform.app.base.exception.YxException;
import com.yxzhishu.platform.app.base.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/11/15 11:33
 * @remark:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionConfigure {
    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R validatedHandler(MethodArgumentNotValidException e){
        log.error("参数校验异常：{}",e.toString(),e);
        return R.error(ErrorCodeEnum.CLIENT_REQUEST_ERROR,
                e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()).toString()
        );
    }


    /**
     * 自定义运行时异常
     * @param e Exception
     * @return JsonResult
     */
    @ExceptionHandler(YxException.class)
    public R handleException(YxException e) {
        return R.error(e.getBaseErrorCodeEnum(),e.getMessage());
    }

    // saToken异常
    @ExceptionHandler(SaTokenException.class)
    public R handlerException(SaTokenException e) {
        log.error("saToken异常：{}",e.getMessage(),e);
        List<Integer> list = Arrays.asList(11001, 11012, 11013, 11014, 11015, 11016);
        if (list.contains(e.getCode())){
            return R.error(ErrorCodeEnum.CLIENT_TOKEN_NOT,e.getMessage());
        }

        return R.error(ErrorCodeEnum.CLIENT_TOKEN_ERROR,e.getMessage());
    }
    /**
     * 全局异常
     * @param e Exception
     * @return JsonResult
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleException(Exception e) {
        log.error("全局异常：{}",e.toString(),e);
        return R.error(ErrorCodeEnum.SERVER_ERROR,e.getMessage());
    }
}

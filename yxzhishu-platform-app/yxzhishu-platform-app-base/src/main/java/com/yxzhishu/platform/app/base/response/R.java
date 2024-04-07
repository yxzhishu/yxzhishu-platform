package com.yxzhishu.platform.app.base.response;

import com.yxzhishu.platform.app.base.enums.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @classDesc: 返回值封装
 * @author: 王朔
 * @date: 2023/8/15 10:47
 * @remark:
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private String code;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;
    /**
     * 返回时间
     */
    private Date responseDate;

    /**
     * 自定义编码及消息
     * @param code
     * @param message
     */
    private R(String code, String message) {
        this.code = code;
        this.message = message;
        this.responseDate = new Date();
    }

    /**
     * 自定义编码、消息及数据
     * @param code
     * @param message
     * @param data
     */
    private R(String code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    /**
     * 成功，无返回数据
     * @return
     * @param <T>
     */
    public static <T> R<T> success() {
        return new R<T>(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 成功，填充返回数据
     * @param data
     * @return
     * @param <T>
     */
    public static <T> R<T> success(T data) {
        return new R<T>(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 错误，填充编码
     * @param code
     * @return
     * @param <T>
     */
    public static <T> R<T> error(ErrorCodeEnum code) {
        return new R<T>(code.getCode(), code.getMessage());
    }

    /**
     * 错误，填充编码替换消息
     * @param code
     * @param error
     * @return
     * @param <T>
     */
    public static <T> R<T> error(ErrorCodeEnum code, String error) {
        return new R<T>(code.getCode(), error);
    }

}

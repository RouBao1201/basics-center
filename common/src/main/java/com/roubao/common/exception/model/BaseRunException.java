package com.roubao.common.exception.model;

/**
 * 基础运行异常
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/30
 **/
public class BaseRunException extends RuntimeException {
    private static final long serialVersionUID = -5157799854488729117L;

    public static final Integer BUSINESS_ERROR = 500;

    private Integer code;

    public BaseRunException() {
    }

    public BaseRunException(Integer code) {
        this.code = code;
    }

    public BaseRunException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String getMessage() {
        if (this.code != null) {
            return "[" + this.code + "]:" + super.getMessage();
        }
        return super.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

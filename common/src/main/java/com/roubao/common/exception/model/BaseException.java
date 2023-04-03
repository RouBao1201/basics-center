package com.roubao.common.exception.model;

/**
 * 基础异常
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/30
 **/
public class BaseException extends Exception {
    private static final long serialVersionUID = 4440788540023696622L;

    private Integer code;

    public BaseException() {
    }

    public BaseException(Integer code) {
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer code, String message) {
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

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

    private Integer errorCode;

    public BaseException() {
    }

    public BaseException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer errorCode, String message) {
        super(message);
        setErrorCode(errorCode);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        setErrorCode(errorCode);
    }

    @Override
    public String getMessage() {
        if (this.errorCode != null) {
            return "[" + this.errorCode + "]:" + super.getMessage();
        }
        return super.getMessage();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}

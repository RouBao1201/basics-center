package com.roubao.common.exception.model;

/**
 * 基础运行时异常
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/30
 **/
public class BaseRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -5157799854488729117L;

    public static final Integer BASE_ERROR_CODE = 500;

    private Integer errorCode;

    public BaseRuntimeException() {
    }

    public BaseRuntimeException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(Integer errorCode, String message, Throwable cause) {
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

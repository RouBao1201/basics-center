package com.roubao.common.exception.enums;

/**
 * 异常编码枚举
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/30
 **/
public enum ExceptionCode {
    /**
     * 业务异常
     */
    SERVICE_EXCEPTION(500, "业务异常");

    private final Integer code;

    private final String message;

    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.roubao.web.response.enums;

/**
 * 响应编码枚举
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/4
 **/
public enum RespCode {
    /**
     * 成功200
     */
    SUCCESS_200(200, "success"),
    /**
     * 成功1
     */
    SUCCESS_1(1, "success"),
    /**
     * 失败0
     */
    FAIL_0(0, "failure"),
    /**
     * 异常500
     */
    ERROR_500(500, "error");

    private final Integer code;

    private final String message;

    RespCode(Integer code, String message) {
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

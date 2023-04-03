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
    SUCCESS_200(200, "成功"),
    /**
     * 失败500
     */
    FAIL_500(500, "失败"),
    /**
     * 成功0
     */
    SUCCESS_0(0, "成功"),
    /**
     * 失败1
     */
    FAIL_1(1, "失败");

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

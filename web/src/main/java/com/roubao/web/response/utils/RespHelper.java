package com.roubao.web.response.utils;

import com.roubao.web.response.dto.RespResult;
import com.roubao.web.response.enums.RespCode;

/**
 * 响应工具类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/4
 **/
public class RespHelper {

    /**
     * 成功响应
     *
     * @param message 响应信息
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> success(String message) {
        return build(RespCode.SUCCESS_200.getCode(), message, null);
    }

    /**
     * 成功响应
     *
     * @param code 响应编码
     * @param message 响应信息
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> success(Integer code, String message) {
        return build(code, message, null);
    }

    /**
     * 成功响应
     *
     * @param message 响应信息
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> success(String message, T data) {
        return build(RespCode.SUCCESS_200.getCode(), message, data);
    }

    /**
     * 成功响应
     * 
     * @param data 响应数据
     * @return RespResult
     * @param <T> 数据泛型
     */
    public static <T> RespResult<T> success(T data) {
        return build(RespCode.SUCCESS_200.getCode(), RespCode.SUCCESS_200.getMessage(), data);
    }

    /**
     * 失败响应
     *
     * @param message 响应信息
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> fail(String message) {
        return build(RespCode.FAIL_500.getCode(), message, null);
    }

    /**
     * 失败响应
     *
     * @param message 响应信息
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> fail(String message, T data) {
        return build(RespCode.FAIL_500.getCode(), message, data);
    }

    /**
     * 失败响应
     *
     * @param code 响应编码
     * @param message 响应信息
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> fail(Integer code, String message) {
        return build(code, message, null);
    }

    /**
     * 构造基础响应体
     *
     * @param code 响应编码
     * @param message 响应信息
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> build(Integer code, String message, T data) {
        return new RespResult<>(code, message, data);
    }

    /**
     * 私有构造方法
     */
    private RespHelper() {

    }
}

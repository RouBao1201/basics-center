package com.roubao.web.response.dto;

import com.roubao.web.response.enums.RespCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 基础响应体
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/4
 **/
@ApiModel("统一响应实体")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RespResult<T> implements Serializable {

    private static final long serialVersionUID = 6621781246351039577L;

    /**
     * 响应编码
     */
    @ApiModelProperty("响应编码")
    private Integer code;

    /**
     * 响应信息
     */
    @ApiModelProperty("响应信息")
    private String message;

    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private T data;

    /**
     * 成功响应
     *
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> success() {
        return new RespResult<>(RespCode.SUCCESS_200.getCode(), RespCode.SUCCESS_200.getMessage(), null);
    }

    /**
     * 成功响应
     *
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> success(T data) {
        return new RespResult<>(RespCode.SUCCESS_200.getCode(), RespCode.SUCCESS_200.getMessage(), data);
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
        return new RespResult<>(RespCode.SUCCESS_200.getCode(), message, data);
    }

    /**
     * 成功响应
     *
     * @param code 响应编码
     * @param message 响应信息
     * @param <T> 数据枚举
     * @return RespResult
     */
    public static <T> RespResult<T> success(Integer code, String message) {
        return new RespResult<>(code, message, null);
    }

    /**
     * 成功响应
     *
     * @param code 响应编码
     * @param message 响应信息
     * @param data 响应数据
     * @param <T> 数据枚举
     * @return RespResult
     */
    public static <T> RespResult<T> success(Integer code, String message, T data) {
        return new RespResult<>(code, message, data);
    }

    /**
     * 失败响应
     *
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> fail() {
        return new RespResult<>(RespCode.FAIL_0.getCode(), RespCode.FAIL_0.getMessage(), null);
    }

    /**
     * 失败响应
     *
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> fail(T data) {
        return new RespResult<>(RespCode.FAIL_0.getCode(), RespCode.FAIL_0.getMessage(), data);
    }

    /**
     * 失败响应
     *
     * @param message 响应信息
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> fail(String message) {
        return new RespResult<>(RespCode.FAIL_0.getCode(), message, null);
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
        return new RespResult<>(RespCode.FAIL_0.getCode(), message, data);
    }

    /**
     * 失败响应
     *
     * @param message 响应信息
     * @param code 响应编码
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> fail(Integer code, String message) {
        return new RespResult<>(code, message, null);
    }

    /**
     * 失败响应
     *
     * @param message 响应信息
     * @param code 响应编码
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> fail(Integer code, String message, T data) {
        return new RespResult<>(code, message, data);
    }

    /**
     * 异常响应
     *
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> error() {
        return new RespResult<>(RespCode.ERROR_500.getCode(), RespCode.ERROR_500.getMessage(), null);
    }

    /**
     * 异常响应
     *
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> error(T data) {
        return new RespResult<>(RespCode.ERROR_500.getCode(), RespCode.ERROR_500.getMessage(), data);
    }

    /**
     * 异常响应
     *
     * @param message 响应信息
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> error(String message) {
        return new RespResult<>(RespCode.ERROR_500.getCode(), message, null);
    }

    /**
     * 异常响应
     *
     * @param message 响应信息
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> error(String message, T data) {
        return new RespResult<>(RespCode.ERROR_500.getCode(), message, data);
    }

    /**
     * 异常响应
     *
     * @param message 响应信息
     * @param code 响应编码
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> error(Integer code, String message) {
        return new RespResult<>(code, message, null);
    }

    /**
     * 异常响应
     *
     * @param message 响应信息
     * @param code 响应编码
     * @param data 响应数据
     * @param <T> 数据泛型
     * @return RespResult
     */
    public static <T> RespResult<T> error(Integer code, String message, T data) {
        return new RespResult<>(code, message, data);
    }
}

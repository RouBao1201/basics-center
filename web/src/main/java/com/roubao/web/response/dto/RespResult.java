package com.roubao.web.response.dto;


import java.io.Serializable;

/**
 * 基础响应体
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/4
 **/
public class RespResult<T> implements Serializable {

    private static final long serialVersionUID = 6621781246351039577L;

    private Integer code;

    private String message;

    private T data;

    public RespResult() {
    }

    public RespResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespResult{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}

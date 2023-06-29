package com.roubao.common.exception.handler;

import com.roubao.common.exception.enums.ExceptionCode;
import com.roubao.common.exception.model.ServiceException;

/**
 * 异常处理类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/30
 **/
public class ExceptionHandler {
    /**
     * 抛出业务异常
     *
     * @param message 异常信息
     */
    public static void throwServiceException(String message) {
        throw new ServiceException(ExceptionCode.SERVICE_EXCEPTION.getCode(), message);
    }

    /**
     * 抛出业务异常
     *
     * @param exceptionCode exceptionCode
     */
    public static void throwServiceException(ExceptionCode exceptionCode) {
        throw new ServiceException(exceptionCode.getCode(), exceptionCode.getMessage());
    }

    /**
     * 抛出业务异常
     *
     * @param code 异常编码
     * @param message 异常信息
     */
    public static void throwServiceException(Integer code, String message) {
        throw new ServiceException(code, message);
    }

    /**
     * 抛出业务异常
     *
     * @param cause 异常类
     */
    public static void throwServiceException(Throwable cause) {
        throw new ServiceException(cause);
    }

    /**
     * 抛出业务异常
     *
     * @param message 异常信息
     * @param cause 异常类
     */
    public static void throwServiceException(String message, Throwable cause) {
        throw new ServiceException(message, cause);
    }

    private ExceptionHandler() {

    }
}

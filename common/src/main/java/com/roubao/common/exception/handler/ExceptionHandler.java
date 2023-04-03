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
     * @return BaseRunException
     */
    public static ServiceException publishServiceException(String message) {
        throw new ServiceException(ExceptionCode.SERVICE_EXCEPTION.getCode(), message);
    }

    /**
     * 抛出业务异常
     *
     * @param exceptionCode exceptionCode
     * @return ServiceException
     */
    public static ServiceException publishServiceException(ExceptionCode exceptionCode) {
        throw new ServiceException(exceptionCode.getCode(), exceptionCode.getMessage());
    }

    /**
     * 抛出业务异常
     *
     * @param code    异常编码
     * @param message 异常信息
     * @return BaseRunException
     */
    public static ServiceException publishServiceException(Integer code, String message) {
        throw new ServiceException(code, message);
    }

    private ExceptionHandler() {

    }
}

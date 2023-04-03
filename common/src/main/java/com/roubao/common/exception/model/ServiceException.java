package com.roubao.common.exception.model;

/**
 * 业务异常
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/30
 **/
public class ServiceException extends BaseRunException {
    private static final long serialVersionUID = 1161570436154544379L;

    public ServiceException() {
    }

    public ServiceException(Integer code) {
        super(code);
    }

    public ServiceException(Integer code, String message) {
        super(code, message);
    }
}

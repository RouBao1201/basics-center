package com.roubao.web.common.logrecord;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 日志记录数据传递对象
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/6
 **/
@Data
@ToString
public class LogRecordDTO<T> implements Serializable {
    private static final long serialVersionUID = -2639214979553533679L;

    /**
     * 类名称
     */
    private String className;

    /**
     * 方法名称
     */

    private String methodName;

    /**
     * url
     */
    private String url;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 描述
     */
    private String description;

    /**
     * 方法第一个参数
     */
    private T param;

    /**
     * 注解
     */
    private LogRecord annotation;
}

package com.roubao.web.common.logrecord;

import java.lang.annotation.*;

/**
 * 日志记录注解
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/6
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecord {
    /**
     * 描述信息
     * 
     * @return String
     */
    String description() default "";

    /**
     * 记录策略
     * 
     * @return LogRecordStrategy
     */
    Class<? extends LogRecordStrategy> strategyClass() default DefaultLogRecordStrategy.class;

    /**
     * 运行模式
     * 
     * @return RunMode
     */
    RunMode runMode() default RunMode.SYNC;

    /**
     * 日志记录-运行模式枚举
     */
    enum RunMode {
        /**
         * 同步记录
         */
        SYNC,
        /**
         * 异步记录
         */
        ASYNC;
    }
}

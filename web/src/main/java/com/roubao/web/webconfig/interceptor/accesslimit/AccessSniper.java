package com.roubao.web.webconfig.interceptor.accesslimit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防刷自定义注解
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/7
 **/
@Target({
    ElementType.METHOD, ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessSniper {
    /**
     * 访问时长区间（单位毫秒）
     * 
     * @return long
     */
    long seconds() default 5000L;

    /**
     * 时间区间内限制访问的次数
     * 
     * @return long
     */
    long accessTimes() default 3000L;

    /**
     * 超出限制后,禁止访问的时间（单位毫秒）
     * 
     * @return long
     */
    long lockTime() default 60000L;
}

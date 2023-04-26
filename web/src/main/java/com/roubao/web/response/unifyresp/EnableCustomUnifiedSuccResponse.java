package com.roubao.web.response.unifyresp;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动统一成功响应自定义注解
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/19
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UnifiedSuccResponseConfiguration.class)
public @interface EnableCustomUnifiedSuccResponse {

    enum RunMode {
        AUTO, MANUAL;
    }
}

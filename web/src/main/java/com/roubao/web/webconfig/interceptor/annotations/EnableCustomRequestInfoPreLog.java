package com.roubao.web.webconfig.interceptor.annotations;

import com.roubao.web.webconfig.interceptor.config.RequestInfoWebConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/4
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RequestInfoWebConfig.class)
public @interface EnableCustomRequestInfoPreLog {
}

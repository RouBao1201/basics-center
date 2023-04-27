package com.roubao.common.sensitive.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.roubao.common.sensitive.config.DesensitizeJsonSerializerConfig;
import com.roubao.common.sensitive.enums.DesensitizeStrategy;

/**
 * 脱敏自定义注解
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/4
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside // 这个注解用来标记Jackson复合注解,当你使用多个Jackson注解组合成一个自定义注解时会用到它
@JsonSerialize(using = DesensitizeJsonSerializerConfig.class) // 指定使用自定义的序列化器
public @interface Desensitize {
    /**
     * 脱敏策略
     *
     * @return DesensitizeStrategy
     */
    DesensitizeStrategy value() default DesensitizeStrategy.DEFAULT;
}

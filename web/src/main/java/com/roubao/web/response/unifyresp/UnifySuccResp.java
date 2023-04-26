package com.roubao.web.response.unifyresp;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 统一成功响应注解
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/20
 **/
@Target({
    ElementType.METHOD, ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnifySuccResp {
    /**
     * 成功响应编码
     * 
     * @return int
     */
    int code() default 200;

    /**
     * 成功响应信息
     * 
     * @return String
     */
    String message() default "success";
}

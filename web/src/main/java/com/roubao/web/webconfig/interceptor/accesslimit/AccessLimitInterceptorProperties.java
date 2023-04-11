package com.roubao.web.webconfig.interceptor.accesslimit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.ToString;

/**
 * 接口防刷配置类
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/7
 **/
@Data
@ToString
@ConfigurationProperties("interceptor.access-limit")
public class AccessLimitInterceptorProperties {
    /**
     * 需要拦截的路径
     */
    private String[] interceptPatterns = {
        "/**"
    };

    /**
     * 无需拦截的路径
     */
    private String[] excludePatterns = {
        "/", "/css/**", "/fonts/**", "/images/**", "/js/**"
    };
}

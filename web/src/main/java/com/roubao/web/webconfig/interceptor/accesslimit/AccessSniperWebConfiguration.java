package com.roubao.web.webconfig.interceptor.accesslimit;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

/**
 * 接口防刷web配置类
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/7
 **/
@Slf4j
@EnableConfigurationProperties(AccessSniperInterceptorProperties.class)
public class AccessSniperWebConfiguration implements WebMvcConfigurer {

    private final AccessSniperInterceptorProperties accessSniperInterceptorProperties;

    public AccessSniperWebConfiguration(AccessSniperInterceptorProperties accessSniperInterceptorProperties) {
        this.accessSniperInterceptorProperties = accessSniperInterceptorProperties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("AccessLimitWebConfiguration ==> addInterceptors configuration properties:{}.",
            accessSniperInterceptorProperties);
        registry.addInterceptor(new AccessSniperInterceptor())
            .addPathPatterns(accessSniperInterceptorProperties.getInterceptPatterns())
            .excludePathPatterns(accessSniperInterceptorProperties.getExcludePatterns());
    }
}

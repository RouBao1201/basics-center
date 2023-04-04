package com.roubao.web.webconfig.interceptor.config;

import com.roubao.web.webconfig.interceptor.RequestInfoInterceptor;
import com.roubao.web.webconfig.interceptor.properties.RequestInfoInterceptorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求信息拦截器配置
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/4
 **/
@Configuration
@EnableConfigurationProperties(RequestInfoInterceptorProperties.class)
public class RequestInfoWebConfig implements WebMvcConfigurer {

    private final RequestInfoInterceptorProperties requestInfoInterceptorProperties;

    public RequestInfoWebConfig(RequestInfoInterceptorProperties requestInfoInterceptorProperties) {
        this.requestInfoInterceptorProperties = requestInfoInterceptorProperties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPattern 添加拦截规则 /** 拦截所有包括静态资源
        // excludePathPattern 排除拦截规则 所以我们需要放开静态资源的拦截
        registry.addInterceptor(new RequestInfoInterceptor())
                .addPathPatterns(requestInfoInterceptorProperties.getInterceptPatterns())
                .excludePathPatterns(requestInfoInterceptorProperties.getExcludePatterns());
    }
}

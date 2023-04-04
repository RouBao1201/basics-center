package com.roubao.web.webconfig.interceptor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;

/**
 * 请求信息拦截器配置
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/4
 **/
@ConfigurationProperties("interceptor.request-info-pre")
public class RequestInfoInterceptorProperties {
    /**
     * 需要拦截的路径
     */
    private String[] interceptPatterns = {"/**"};

    /**
     * 无需拦截的路径
     */
    private String[] excludePatterns = {"/", "/css/**", "/fonts/**", "/images/**", "/js/**"};

    public String[] getInterceptPatterns() {
        return interceptPatterns;
    }

    public void setInterceptPatterns(String[] interceptPatterns) {
        this.interceptPatterns = interceptPatterns;
    }

    public String[] getExcludePatterns() {
        return excludePatterns;
    }

    public void setExcludePatterns(String[] excludePatterns) {
        this.excludePatterns = excludePatterns;
    }

    @Override
    public String toString() {
        return "RequestInfoInterceptorProperties{" +
                "interceptPatterns=" + Arrays.toString(interceptPatterns) +
                ", excludePatterns=" + Arrays.toString(excludePatterns) +
                '}';
    }
}

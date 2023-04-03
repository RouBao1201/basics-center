package com.roubao.web.rest.autoconfiguration;

import com.roubao.web.rest.properties.OKHttpProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate自动装配
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@Slf4j
@Order(1)
@Configuration
@EnableConfigurationProperties(OKHttpProperties.class)
public class RestTemplateAutoConfiguration {

    private final OKHttpProperties okHttpProperties;

    public RestTemplateAutoConfiguration(OKHttpProperties okHttpProperties) {
        this.okHttpProperties = okHttpProperties;
    }

    @Bean("restTemplate")
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate() {
        log.info("RestTemplateAutoConfiguration ==> Start custom autoConfiguration [RestTemplate] bean.");
        log.info("RestTemplateAutoConfiguration ==> OKHttpProperties: {}.", okHttpProperties);
        ClientHttpRequestFactory factory = httpRequestFactory();
        return new RestTemplate(factory);
    }

    /**
     * Http工厂
     *
     * @return ClientHttpRequestFactory
     */
    @Bean("okHttp3ClientHttpRequestFactory")
    @ConditionalOnMissingBean(OkHttp3ClientHttpRequestFactory.class)
    public OkHttp3ClientHttpRequestFactory httpRequestFactory() {
        return new OkHttp3ClientHttpRequestFactory(okHttpConfigClient());
    }

    /**
     * 客户端
     *
     * @return OkHttpClient
     */
    @Bean("okHttpClient")
    @ConditionalOnMissingBean(OkHttpClient.class)
    public OkHttpClient okHttpConfigClient() {
        return new OkHttpClient().newBuilder()
                .connectionPool(pool())
                .connectTimeout(okHttpProperties.getConnectTimeout(), okHttpProperties.getConnectTimeoutTimeUnit())
                .readTimeout(okHttpProperties.getReadTimeout(), okHttpProperties.getReadTimeoutTimeUnit())
                .writeTimeout(okHttpProperties.getWriteTimeout(), okHttpProperties.getWriteTimeoutTimeUnit())
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

    /**
     * 连接池
     *
     * @return ConnectionPool
     */
    private ConnectionPool pool() {
        return new ConnectionPool(okHttpProperties.getMaxIdleConnections(), okHttpProperties.getKeepAliveDuration(), okHttpProperties.getKeepAliveDurationTimeUnit());
    }
}

package com.roubao.common.thread.autoconfiguration;

import com.roubao.common.thread.bean.ThreadPoolHandler;
import com.roubao.common.thread.properties.ThreadPoolProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池工具自动装配类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/23
 **/
@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadPoolAutoConfiguration {

    private final ThreadPoolProperties threadPoolProperties;

    public ThreadPoolAutoConfiguration(ThreadPoolProperties threadPoolProperties) {
        this.threadPoolProperties = threadPoolProperties;
    }

    @Bean("threadPoolHandler")
    @ConditionalOnMissingBean(ThreadPoolHandler.class)
    public ThreadPoolHandler threadPoolHolder() {
        return new ThreadPoolHandler(threadPoolProperties);
    }
}

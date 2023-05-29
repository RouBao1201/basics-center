package com.roubao.common.thread.autoconfiguration;

import com.roubao.common.thread.bean.ThreadPoolHandler;
import com.roubao.common.thread.properties.ThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadPoolAutoConfiguration {

    private static final String BANNER_I_THREADPOOL_AUTO =
            "   ___     _____  _  _  ___  ___  ___  ___   ___   ___    ___   _        ___  _   _  _____   ___" + System.lineSeparator() +
            "  |_ _|   |_   _|| || || _ \\| __|/   \\|   \\ | _ \\ / _ \\  / _ \\ | |      /   \\| | | ||_   _| / _ \\" + System.lineSeparator() +
            "   | |      | |  | __ ||   /| _| | - || |) ||  _/| (_) || (_) || |__    | - || |_| |  | |  | (_) |" + System.lineSeparator() +
            "  |___|     |_|  |_||_||_|_\\|___||_|_||___/ |_|   \\___/  \\___/ |____|   |_|_| \\___/   |_|   \\___/";

    private final ThreadPoolProperties threadPoolProperties;

    public ThreadPoolAutoConfiguration(ThreadPoolProperties threadPoolProperties) {
        this.threadPoolProperties = threadPoolProperties;
    }

    @Bean("threadPoolHandler")
    @ConditionalOnMissingBean(ThreadPoolHandler.class)
    public ThreadPoolHandler threadPoolHolder() {
        log.info(System.lineSeparator() + BANNER_I_THREADPOOL_AUTO + System.lineSeparator());
        log.info("ThreadPoolAutoConfiguration ==> Start custom autoConfiguration [ThreadPoolHandler] bean.");
        log.info("ThreadPoolAutoConfiguration ==> ThreadPoolProperties configuration: {}.", threadPoolProperties);
        return new ThreadPoolHandler(threadPoolProperties);
    }
}

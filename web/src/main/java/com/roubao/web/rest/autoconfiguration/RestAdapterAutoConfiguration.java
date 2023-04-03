package com.roubao.web.rest.autoconfiguration;

import com.roubao.web.rest.bean.RestAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestAdapter自动装配
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@Slf4j
@Configuration
public class RestAdapterAutoConfiguration {
    @Bean("restAdapter")
    @ConditionalOnBean(RestTemplate.class)
    @ConditionalOnMissingBean(RestAdapter.class)
    public RestAdapter restAdapter(RestTemplate restTemplate) {
        log.info("RestAdapterAutoConfiguration ==> Start autoConfiguration RestAdapter.");
        return new RestAdapter(restTemplate);
    }
}

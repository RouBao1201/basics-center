package com.roubao.web.rest.autoconfiguration;

import com.roubao.web.rest.bean.RestAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

/**
 * RestAdapter自动装配
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@Slf4j
@Order(2)
@Configuration
public class RestAdapterAutoConfiguration {
    @Bean("restAdapter")
    @ConditionalOnMissingBean(RestAdapter.class)
    public RestAdapter restAdapter(RestTemplate restTemplate) {
        log.info("RestAdapterAutoConfiguration ==> Start custom autoConfiguration [RestAdapter] bean.");
        return new RestAdapter(restTemplate);
    }
}

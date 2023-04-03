package com.roubao.web.rest.autoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@Slf4j
@Order(1)
@Configuration
public class RestTemplateAutoConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        log.info("RestTemplateAutoConfiguration ==> Start custom autoConfiguration [RestTemplate] bean.");
        return builder.build();
    }
}

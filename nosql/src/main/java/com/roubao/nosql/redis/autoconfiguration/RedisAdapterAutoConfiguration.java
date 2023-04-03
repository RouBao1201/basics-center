package com.roubao.nosql.redis.autoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

import com.roubao.nosql.redis.bean.RedisAdapter;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/28
 **/
@Slf4j
@Order(2)
@Configuration
public class RedisAdapterAutoConfiguration {

    @Bean("redisAdapter")
    @ConditionalOnMissingBean(RedisAdapter.class)
    public RedisAdapter redisAdaptor(RedisTemplate redisTemplate) {
        log.info("RedisAdapterAutoConfiguration ==> Start custom autoConfiguration [RedisAdapter] bean.");
        return new RedisAdapter(redisTemplate);
    }
}

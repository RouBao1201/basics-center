package com.roubao.nosql.redis.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.roubao.nosql.redis.bean.RedisAdapter;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/28
 **/
@Configuration
public class RedisAdaptorAutoConfiguration {

    @Bean("redisAdaptor")
    @ConditionalOnMissingBean(RedisAdapter.class)
    public RedisAdapter redisAdaptor(RedisTemplate redisTemplate) {
        return new RedisAdapter(redisTemplate);
    }
}

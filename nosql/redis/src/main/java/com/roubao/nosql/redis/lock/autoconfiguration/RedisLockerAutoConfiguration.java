package com.roubao.nosql.redis.lock.autoconfiguration;

import com.roubao.nosql.redis.lock.bean.RedisLocker;
import com.roubao.nosql.redis.lock.properties.RedisLockerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * RedisLocker自动装配类
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/21
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(RedisLockerProperties.class)
public class RedisLockerAutoConfiguration {

    private final RedisLockerProperties redisLockerProperties;

    public RedisLockerAutoConfiguration(RedisLockerProperties redisLockerProperties) {
        this.redisLockerProperties = redisLockerProperties;
    }

    /**
     * 创建多例redis锁
     * 
     * @param stringRedisTemplate stringRedisTemplate
     * @return RedisLocker
     */
    @Order(1)
    @Bean("redisLocker")
    @ConditionalOnMissingBean(RedisLocker.class)
    public RedisLocker redisLocker(StringRedisTemplate stringRedisTemplate) {
        log.info("RedisLockerAutoConfiguration ==> Start custom autoConfiguration [RedisLocker] bean.");
        log.info("RedisLockerAutoConfiguration ==> RedisLockerProperties configuration: {}.", redisLockerProperties);
        return new RedisLocker(redisLockerProperties, stringRedisTemplate);
    }
}

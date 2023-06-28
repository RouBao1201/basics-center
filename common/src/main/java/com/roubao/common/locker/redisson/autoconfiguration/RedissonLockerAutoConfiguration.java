package com.roubao.common.locker.redisson.autoconfiguration;

import com.roubao.common.locker.redisson.bean.RedissonLocker;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Redisson自动装配类
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/27
 **/
@Slf4j
public class RedissonLockerAutoConfiguration {

    /**
     * 创建redissonLocker bean
     * 
     * @param redissonClient redisson客户端
     * @return RedissonLocker
     */
    @Bean("redissonLocker")
    @ConditionalOnMissingBean(RedissonLocker.class)
    public RedissonLocker redissonLocker(RedissonClient redissonClient) {
        log.info("RedissonLockerAutoConfiguration ==> Start custom autoConfiguration [RedissonLocker] bean.");
        return new RedissonLocker(redissonClient);
    }
}

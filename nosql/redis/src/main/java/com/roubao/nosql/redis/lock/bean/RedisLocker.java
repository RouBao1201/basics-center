package com.roubao.nosql.redis.lock.bean;

import com.roubao.nosql.redis.lock.properties.RedisLockerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * redis锁构造器
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/23
 **/
@Slf4j
public class RedisLocker {

    private final RedisLockerProperties redisLockerProperties;

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLocker(RedisLockerProperties redisLockerProperties, StringRedisTemplate stringRedisTemplate) {
        this.redisLockerProperties = redisLockerProperties;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 创建redis锁
     *
     * @param lockName 锁名称（唯一）
     * @return IRedisLock
     */
    public RedisLock createLock(String lockName) {
        return new RedisLock(lockName, getRedisLockerProperties(), getStringRedisTemplate());
    }

    public RedisLockerProperties getRedisLockerProperties() {
        return redisLockerProperties;
    }

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }
}

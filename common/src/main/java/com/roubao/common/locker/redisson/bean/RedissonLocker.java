package com.roubao.common.locker.redisson.bean;

import com.roubao.common.locker.ILocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * RedissonLocker分布式锁
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/27
 **/
public class RedissonLocker implements ILocker {

    private final RedissonClient redissonClient;

    public RedissonLocker(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public RLock createLock(String lockName) {
        return redissonClient.getLock(lockName);
    }
}

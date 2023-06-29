package com.roubao.common.locker.functions;

import java.util.concurrent.TimeUnit;

import com.roubao.common.exception.enums.ExceptionCode;
import com.roubao.common.locker.redis.bean.RedisLock;
import com.roubao.common.locker.redis.bean.RedisLocker;

import lombok.SneakyThrows;

/**
 * redis分布式锁执行类
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/29
 **/
public class RedisLockExecutor {

    private final RedisLocker redisLocker;

    public RedisLockExecutor(RedisLocker redisLocker) {
        this.redisLocker = redisLocker;
    }

    @SneakyThrows
    public <T> T executeWithLockThrows(String lockName, SupplierThrows<T> supplier) {
        RedisLock lock = redisLocker.createLock(lockName);
        try {
            if (lock.tryLock()) {
                return supplier.get();
            }
            else {
                throw new RuntimeException(ExceptionCode.LOCK_LIMIT_EXCEPTION.getMessage());
            }
        }
        finally {
            lock.unlock();
        }
    }

    @SneakyThrows
    public <T> T executeWithLockThrows(String lockName, long leaseTime, TimeUnit timeUnit, SupplierThrows<T> supplier) {
        RedisLock lock = redisLocker.createLock(lockName);
        try {
            if (lock.tryLock(leaseTime, timeUnit)) {
                return supplier.get();
            }
            else {
                throw new RuntimeException(ExceptionCode.LOCK_LIMIT_EXCEPTION.getMessage());
            }
        }
        finally {
            lock.unlock();
        }
    }

    @FunctionalInterface
    public interface SupplierThrows<T> {
        /**
         * 执行逻辑
         * 
         * @return T
         * @throws Throwable
         */
        T get() throws Throwable;
    }
}

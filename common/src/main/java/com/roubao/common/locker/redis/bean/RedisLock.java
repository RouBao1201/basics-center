package com.roubao.common.locker.redis.bean;

import com.roubao.common.locker.redis.properties.RedisLockerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Redis锁（自定义-纯学习）
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/27
 **/
@Slf4j
public class RedisLock implements Lock {

    private final String lockName;

    private final RedisLockerProperties redisLockerProperties;

    private final String singleId;

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLock(String lockName, RedisLockerProperties redisLockerProperties,
        StringRedisTemplate stringRedisTemplate) {
        this.lockName = lockName;
        this.redisLockerProperties = redisLockerProperties;
        this.stringRedisTemplate = stringRedisTemplate;
        this.singleId = Thread.currentThread().getId() + ":" + UUID.randomUUID();
    }

    @Override
    public void lock() {
        tryLock();
    }

    /**
     * 加锁(锁过期时间为默认30s)
     *
     * @return 是否成功加锁
     */
    @Override
    public boolean tryLock() {
        return tryLock(redisLockerProperties.getExpire(), TimeUnit.MILLISECONDS);
    }

    /**
     * 加锁
     *
     * @param expire   锁过期时间
     * @param timeUnit 时间单位
     * @return 是否成功加锁
     */
    public boolean tryLock(long expire, TimeUnit timeUnit) {
        String luaScript = "if redis.call('exists', KEYS[1]) == 0 or redis.call('hexists', KEYS[1], ARGV[1]) == 1 " +
                "then " +
                "   redis.call('hincrby', KEYS[1], ARGV[1], 1) " +
                "   redis.call('expire', KEYS[1], ARGV[2]) " +
                "   return 1 " +
                "else " +
                "   return 0 " +
                "end";

        List<String> keys = Arrays.asList(redisLockerProperties.getLockPrefix() + getLockName());
        String expireTime = String.valueOf(timeUnit.toSeconds(expire));

        // 循环获取锁
        int count = 0;
        while (true) {
            log.info("RedisLocker ==> TryLock {} times. The lockName:{}, lockSingleId:{}", count + 1,
                redisLockerProperties.getLockPrefix() + getLockName(), this.singleId);
            Boolean lockFlag = this.stringRedisTemplate.execute(new DefaultRedisScript<>(luaScript, Boolean.class),
                    keys, this.singleId, expireTime);
            ++count;
            if (Boolean.TRUE.equals(lockFlag)) {
                log.info("RedisLocker ==> TryLock success. The lockName is:[{}].", getLockName());
                // 加锁成功,启动定时器重置过期时间
                renewExpire();
                return true;
            }
            else {
                try {
                    if (redisLockerProperties.getFetchKeyTimes() != -1
                            && count >= redisLockerProperties.getFetchKeyTimes()) {
                        log.error("RedisLocker ==> TryLock failure. The lockName is:[{}].", getLockName());
                        return false;
                    }
                    Thread.sleep(redisLockerProperties.getFetchKeyIntervalTime());
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void unlock() {
        String luaScript = "if redis.call('hexists', KEYS[1], ARGV[1]) == 0 " +
                "then " +
                "   return nil " +
                "elseif redis.call('hincrby', KEYS[1], ARGV[1], -1) == 0 " +
                "   then return redis.call('del', KEYS[1]) " +
                "else " +
                "   return 0 " +
                "end";
        List<String> keys = Arrays.asList(redisLockerProperties.getLockPrefix() + getLockName());
        Long execute = this.stringRedisTemplate.execute(new DefaultRedisScript<>(luaScript, Long.class), keys, getSingleId());

        // 若尝试解开并不属于你的锁则抛出异常
        if (execute == null) {
            throw new IllegalMonitorStateException("RedisLock ==> This lock doesn't belong to you!");
        }
        log.info("Unlock success. LockName is [{}].", getLockName());
    }

    /**
     * 重置过期时间
     */
    private void renewExpire() {
        String script = "if redis.call('hexists', KEYS[1], ARGV[1]) == 1 " +
                "then " + 
                "   return redis.call('expire', KEYS[1], ARGV[2]) " + 
                "else " + 
                "   return 0 " +
                "end";
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String expireTime = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(redisLockerProperties.getExpire()));
                List<String> keys = Arrays.asList(redisLockerProperties.getLockPrefix() + getLockName());
                if (Boolean.TRUE.equals(stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class),
                    keys, getSingleId(), String.valueOf(expireTime)))) {
                    renewExpire();
                }
            }
        }, redisLockerProperties.getRenewExpire());
    }


    public String getLockName() {
        return lockName;
    }

    public String getSingleId() {
        return singleId;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

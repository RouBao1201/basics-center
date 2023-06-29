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
     * @param expire 锁生效时间
     * @param timeUnit 时间单位
     * @return 是否加锁成功
     */
    @Override
    public boolean tryLock(long expire, TimeUnit timeUnit) {
        return tryLock(redisLockerProperties.getLockPrefix() + getLockName(), getSingleId(), expire, timeUnit,
            redisLockerProperties.getFetchLockTimes(), redisLockerProperties.getFetchLockIntervalTime(),
            TimeUnit.MILLISECONDS, redisLockerProperties.getRenewExpire(), TimeUnit.MILLISECONDS);
    }

    /**
     * 加锁
     * 
     * @param lockKey 锁名称
     * @param singleId 唯一id
     * @param expire 锁生效时间
     * @param expireUnit 锁生效时间单位
     * @param fetchLockTimes 获取锁的次数
     * @param fetchLockIntervalTime 获取锁的间隔时间
     * @param fetchLockIntervalTimeUnit 获取锁间隔时间单位
     * @param renewExpire 重置锁的检测时间
     * @param renewExpireUnit 重置锁的检测时间单位
     * @return 是否加锁成功
     */
    public boolean tryLock(String lockKey, String singleId, long expire, TimeUnit expireUnit, int fetchLockTimes,
        long fetchLockIntervalTime, TimeUnit fetchLockIntervalTimeUnit, long renewExpire, TimeUnit renewExpireUnit) {
        String luaScript = "if redis.call('exists', KEYS[1]) == 0 or redis.call('hexists', KEYS[1], ARGV[1]) == 1 " +
                "then " +
                "   redis.call('hincrby', KEYS[1], ARGV[1], 1) " +
                "   redis.call('expire', KEYS[1], ARGV[2]) " +
                "   return 1 " +
                "else " +
                "   return 0 " +
                "end";

        List<String> keys = Arrays.asList(lockKey);
        String expireTime = String.valueOf(expireUnit.toSeconds(expire));

        // 循环获取锁
        int count = 0;
        while (true) {
            log.info("RedisLocker ==> TryLock {} times. The lockName:{}, lockSingleId:{}", count + 1, lockKey,
                this.singleId);
            Boolean lockFlag = this.stringRedisTemplate.execute(new DefaultRedisScript<>(luaScript, Boolean.class),
                keys, singleId, expireTime);
            ++count;
            if (Boolean.TRUE.equals(lockFlag)) {
                log.info("RedisLocker ==> TryLock success. The lockName is:[{}].", lockKey);
                // 加锁成功,启动定时器重置过期时间
                renewExpire(renewExpire, renewExpireUnit);
                return true;
            }
            else {
                try {
                    if (fetchLockTimes != -1 && count >= fetchLockTimes) {
                        log.error("RedisLocker ==> TryLock failure. The lockName is:[{}].", lockKey);
                        return false;
                    }
                    Thread.sleep(fetchLockIntervalTimeUnit.toMillis(fetchLockIntervalTime));
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
            throw new IllegalMonitorStateException("IRedisLock ==> This lock doesn't belong to you!");
        }
        log.info("Unlock success. LockName is [{}].", getLockName());
    }

    /**
     * 重置过期时间
     * 
     * @param renewExpire 重置时间
     * @param renewExpireUnit 重置时间单位
     */
    private void renewExpire(long renewExpire, TimeUnit renewExpireUnit) {
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
                    renewExpire(renewExpire, renewExpireUnit);
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

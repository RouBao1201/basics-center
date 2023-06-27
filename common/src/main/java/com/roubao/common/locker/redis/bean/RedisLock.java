package com.roubao.common.locker.redis.bean;

import com.roubao.common.locker.redis.properties.RedisLockerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Redis锁构造器
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/27
 **/
@Slf4j
public class RedisLock {

    private String lockKey;

    private final RedisLockerProperties redisLockerProperties;

    private final String singleId;

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLock(RedisLockerProperties redisLockerProperties, StringRedisTemplate stringRedisTemplate) {
        this.redisLockerProperties = redisLockerProperties;
        this.stringRedisTemplate = stringRedisTemplate;
        this.singleId = Thread.currentThread().getId() + ":" + UUID.randomUUID();
    }

    /**
     * 加锁(锁过期时间为默认30s)
     *
     * @return 是否成功加锁
     */
    public boolean tryLock(String lockKey) {
        return tryLock(lockKey, redisLockerProperties.getExpire(), TimeUnit.MILLISECONDS);
    }

    /**
     * 加锁
     *
     * @param expire   锁过期时间
     * @param timeUnit 时间单位
     * @return 是否成功加锁
     */
    public boolean tryLock(String lockKey, long expire, TimeUnit timeUnit) {
        setLockKey(lockKey);
        String luaScript = "if redis.call('exists', KEYS[1]) == 0 or redis.call('hexists', KEYS[1], ARGV[1]) == 1 " +
                "then " +
                "   redis.call('hincrby', KEYS[1], ARGV[1], 1) " +
                "   redis.call('expire', KEYS[1], ARGV[2]) " +
                "   return 1 " +
                "else " +
                "   return 0 " +
                "end";

        List<String> keys = Arrays.asList(redisLockerProperties.getLockPrefix() + getLockKey());
        String expireTime = String.valueOf(timeUnit.toSeconds(expire));

        // 循环获取锁
        int count = 0;
        while (true) {
            log.info("RedisLocker ==> TryLock [{}] times.", count + 1);
            Boolean lockFlag = this.stringRedisTemplate.execute(new DefaultRedisScript<>(luaScript, Boolean.class),
                    keys, this.singleId, expireTime);
            ++count;
            if (Boolean.TRUE.equals(lockFlag)) {
                log.info("RedisLocker ==> TryLock success. The lockKey is:[{}].", getLockKey());
                return true;
            }
            else {
                try {
                    if (redisLockerProperties.getFetchKeyTimes() != -1
                            && count >= redisLockerProperties.getFetchKeyTimes()) {
                        log.error("RedisLocker ==> TryLock failure. The lockKey is:[{}].", getLockKey());
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

    /**
     * 解锁
     */
    public Long unLock() {
        String luaScript = "if redis.call('hexists', KEYS[1], ARGV[1]) == 0 " +
                "then " +
                "   return nil " +
                "elseif redis.call('hincrby', KEYS[1], ARGV[1], -1) == 0 " +
                "   then return redis.call('del', KEYS[1]) " +
                "else " +
                "   return 0 " +
                "end";
        List<String> keys = Arrays.asList(redisLockerProperties.getLockPrefix() + getLockKey());
        Long execute = this.stringRedisTemplate.execute(new DefaultRedisScript<>(luaScript, Long.class), keys, getSingleId());

        // 若尝试解开并不属于你的锁则抛出异常
        if (execute == null) {
            throw new IllegalMonitorStateException("RedisLock ==> This lock doesn't belong to you!");
        }
        return execute;
    }

    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public String getSingleId() {
        return singleId;
    }
}

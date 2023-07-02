package com.roubao.nosql.redis.lock.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis锁配置类
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/21
 **/
@ConfigurationProperties(prefix = "locker.redis")
public class RedisLockerProperties {
    /**
     * 锁前缀
     */
    private String lockPrefix = "redisLocker:";

    /**
     * 锁过期时间（单位ms）
     */
    private long expire = 10000;

    /**
     * 循环获取锁的次数（-1为不限制）
     */
    private int fetchLockTimes = -1;

    /**
     * 循环获取锁间隔时间（单位ms）
     */
    private long fetchLockIntervalTime = 1000;

    /**
     * 检测锁过期时间间隔（单位ms）
     */
    private long renewExpire = 5000;

    public String getLockPrefix() {
        return lockPrefix;
    }

    public void setLockPrefix(String lockPrefix) {
        this.lockPrefix = lockPrefix;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public int getFetchLockTimes() {
        return fetchLockTimes;
    }

    public void setFetchLockTimes(int fetchLockTimes) {
        this.fetchLockTimes = fetchLockTimes;
    }

    public long getFetchLockIntervalTime() {
        return fetchLockIntervalTime;
    }

    public void setFetchLockIntervalTime(long fetchLockIntervalTime) {
        this.fetchLockIntervalTime = fetchLockIntervalTime;
    }

    public long getRenewExpire() {
        return renewExpire;
    }

    public void setRenewExpire(long renewExpire) {
        this.renewExpire = renewExpire;
    }

    @Override
    public String toString() {
        return "RedisLockerProperties{" + "lockPrefix='" + lockPrefix + '\'' + ", expire=" + expire + ", fetchLockTimes="
            + fetchLockTimes + ", fetchLockIntervalTime=" + fetchLockIntervalTime + '}';
    }
}

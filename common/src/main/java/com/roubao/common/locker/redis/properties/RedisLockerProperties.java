package com.roubao.common.locker.redis.properties;

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
    private String lockPrefix = "lock:";

    /**
     * 锁过期时间（单位ms）
     */
    private long expire = 30000;

    /**
     * 循环获取锁的次数（-1为不限制）
     */
    private int fetchKeyTimes = -1;

    /**
     * 循环获取锁间隔时间（单位ms）
     */
    private long fetchKeyIntervalTime = 1000;

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

    public int getFetchKeyTimes() {
        return fetchKeyTimes;
    }

    public void setFetchKeyTimes(int fetchKeyTimes) {
        this.fetchKeyTimes = fetchKeyTimes;
    }

    public long getFetchKeyIntervalTime() {
        return fetchKeyIntervalTime;
    }

    public void setFetchKeyIntervalTime(long fetchKeyIntervalTime) {
        this.fetchKeyIntervalTime = fetchKeyIntervalTime;
    }

    @Override
    public String toString() {
        return "RedisLockerProperties{" + "lockPrefix='" + lockPrefix + '\'' + ", expire=" + expire + ", fetchKeyTimes="
            + fetchKeyTimes + ", fetchKeyIntervalTime=" + fetchKeyIntervalTime + '}';
    }
}

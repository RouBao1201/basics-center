package com.roubao.common.thread.properties;

import com.roubao.common.thread.enums.RejectedPolicy;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池配置类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/23
 **/
@ConfigurationProperties(prefix = "thread-pool.configuration")
public class ThreadPoolProperties {
    /**
     * 线程池前缀
     */
    private String prefixName = "roubao-pool";

    /**
     * 等待队列长度
     */
    private int blockingQueueLength = 1000;

    /**
     * 闲置线程存活时间
     */
    private long keepAliveTime = 60000;

    /**
     * 核心线程数
     */
    private int corePoolSize = 10;

    /**
     * 最大线程数
     */
    private int maximumPoolSize = 20;

    /**
     * 拒绝策略
     */
    private RejectedPolicy rejectedPolicy = RejectedPolicy.ABORT;

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public int getBlockingQueueLength() {
        return blockingQueueLength;
    }

    public void setBlockingQueueLength(int blockingQueueLength) {
        this.blockingQueueLength = blockingQueueLength;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public RejectedPolicy getRejectedPolicy() {
        return rejectedPolicy;
    }

    public void setRejectedPolicy(RejectedPolicy rejectedPolicy) {
        this.rejectedPolicy = rejectedPolicy;
    }

    @Override
    public String toString() {
        return "ThreadPoolProperties{" +
                "prefixName='" + prefixName + '\'' +
                ", blockingQueueLength=" + blockingQueueLength +
                ", keepAliveTime=" + keepAliveTime +
                ", corePoolSize=" + corePoolSize +
                ", maximumPoolSize=" + maximumPoolSize +
                ", rejectedPolicy=" + rejectedPolicy +
                '}';
    }
}

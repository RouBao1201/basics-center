package com.roubao.basics.testall.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roubao.common.exception.handler.ExceptionHandler;
import com.roubao.common.locker.functions.RedisLockExecutor;
import com.roubao.common.locker.redis.bean.RedisLock;
import com.roubao.common.locker.redis.bean.RedisLocker;
import com.roubao.common.locker.redisson.bean.RedissonLocker;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 分布式锁测试API
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/29
 **/
@Api(tags = "分布式锁测试API")
@RestController
@RequestMapping("/lockTest")
public class LockTestController {

    private static int ticketCount = 10;

    @Autowired
    private RedisLocker redisLocker;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedissonLocker redissonLocker;

    @Autowired
    private RedisLockExecutor redisLockExecutor;

    @ApiOperation("RedisLocker分布锁测试")
    @GetMapping("/redisLocker")
    public String redisLocker() {
        RedisLock lock = redisLocker.createLock("redis-ticket-lock");
        lock.tryLock();
        int oldTicketCount = ticketCount;
        try {
            if (ticketCount > 0) {
                --ticketCount;
                System.out.println("线程:" + Thread.currentThread().getName() + " 购票结束! 原票数:" + oldTicketCount
                    + ", 购买后剩余:" + ticketCount);
            }
            else {
                System.out.println("线程:" + Thread.currentThread().getName() + " 小子! 你来晚了,票卖完了!!!");
            }
            // 模拟每个人的买票时长2s
            Thread.sleep(2000);
        }
        catch (Exception e) {
            ExceptionHandler.throwServiceException(e);
        }
        finally {
            lock.unlock();
        }
        return "购票完成!";
    }

    @ApiOperation("RedisLockExecutor分布锁测试")
    @GetMapping("/redisLockExecutor")
    public String redisLockExecutor() {
        return redisLockExecutor.executeWithLockThrows("redisLockExecutor-ticket-lock", () -> {
            int oldTicketCount = ticketCount;
            if (ticketCount > 0) {
                --ticketCount;
            }
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(
                "线程:" + Thread.currentThread().getName() + "购票结束! 原票数:" + oldTicketCount + ", 购买后剩余:" + ticketCount);
            return "购票完成!";
        });
    }

    @ApiOperation("RedissonLocker分布锁测试")
    @GetMapping("/redissonLocker")
    public String redissonLocker() {
        RLock lock = redissonLocker.createLock("redisson-ticket-lock");
        lock.lock();
        int oldTicketCount = ticketCount;
        try {
            if (ticketCount > 0) {
                --ticketCount;
            }
            Thread.sleep(2000);
            System.out.println(
                "线程:" + Thread.currentThread().getName() + "购票结束! 原票数:" + oldTicketCount + ", 购买后剩余:" + ticketCount);
        }
        catch (Exception e) {
            ExceptionHandler.throwServiceException(e.getMessage());
        }
        finally {
            lock.unlock();
        }
        return "购票完成!";
    }
}

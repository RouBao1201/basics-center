package com.roubao.basics.testall.retry;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/8
 **/
@Slf4j
@Service
public class RetryTestService {

    @Retryable
    public void retryTest() {
        System.out.println("测试Spring-Retry重试框架! Retryable....");
        throw new RuntimeException("发生异常拉!!!");
    }

    @Recover
    public void retryTestRecover() {
        System.out.println("测试Spring-Retry重试框架! Recover...");
    }
}

package com.roubao.basics.testall.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.roubao.basics.testall.common.TestRequestDTO;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/8
 **/
@Slf4j
@Service
public class RetryTestService {

    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(value = 2000))
    public void retryTest(TestRequestDTO dto) {
        System.out.println(DateUtil.now() + " 执行业务逻辑... " + dto);
        throw new RuntimeException("业务发生异常拉!!!");
    }

    @Recover
    public void retryTestRecover(Exception e, TestRequestDTO dto) {
        System.out.println(DateUtil.now() + " 重试次数已用尽!!! " + dto);
        System.out.println("异常信息:" + e.getMessage());
    }
}

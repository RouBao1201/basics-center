package com.roubao.basics.testall.retry;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/8
 **/
@RestController
@RequestMapping("/retryTest")
public class RetryTestController {

    @Resource
    private RetryTestService retryTestService;

    @GetMapping("/retryTest")
    public void retryTest() {
        retryTestService.retryTest();
    }
}

package com.roubao.basics.testall.retry;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roubao.basics.testall.common.TestRequestDTO;

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

    @PostMapping("/retryTest")
    public void retryTest(@RequestBody TestRequestDTO dto) {
        retryTestService.retryTest(dto);
    }
}

package com.roubao.basics.testall.accesslimit;

import com.roubao.web.webconfig.interceptor.accesslimit.AccessSniper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/11
 **/
@RestController
@RequestMapping("/accessLimit")
public class AccessLimitController {

    @AccessSniper(accessTimes = 1, seconds = 3000, lockTime = 5000)
    @GetMapping("/accessLimitTest")
    public String accessLimitTest() {
        System.out.println("测试接口访问限制拦截器...");
        return "成功";
    }
}

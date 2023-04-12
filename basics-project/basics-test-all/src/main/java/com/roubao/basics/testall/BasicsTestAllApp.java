package com.roubao.basics.testall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roubao.web.webconfig.exceptionhandler.EnableCustomGlobalExceptionHandler;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@EnableRetry // 开启重试框架
@SpringBootApplication
@EnableCustomGlobalExceptionHandler // 开启全局异常处理（自定义）
// @EnableCustomRequestInfoPreLog // 开启请求信息拦截日志输出（自定义）
// @EnableCustomAccessSniper // 开启访问拦截限制（自定义）
public class BasicsTestAllApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicsTestAllApp.class);
        app.run(args);
    }
}

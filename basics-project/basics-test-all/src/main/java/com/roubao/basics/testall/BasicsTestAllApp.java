package com.roubao.basics.testall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roubao.web.webconfig.exceptionhandler.EnableCustomGlobalExceptionHandler;
import com.roubao.web.webconfig.interceptor.accesslimit.EnableCustomAccessSniper;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@SpringBootApplication
@EnableCustomGlobalExceptionHandler
//@EnableCustomRequestInfoPreLog
@EnableCustomAccessSniper
public class BasicsTestAllApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicsTestAllApp.class);
        app.run(args);
    }
}

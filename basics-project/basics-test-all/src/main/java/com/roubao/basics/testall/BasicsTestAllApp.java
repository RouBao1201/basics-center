package com.roubao.basics.testall;

import com.roubao.web.response.annotations.EnableGlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@SpringBootApplication
@EnableGlobalExceptionHandler
public class BasicsTestAllApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicsTestAllApp.class);
        app.run(args);
    }
}

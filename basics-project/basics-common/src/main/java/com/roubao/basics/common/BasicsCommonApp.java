package com.roubao.basics.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@SpringBootApplication
public class BasicsCommonApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicsCommonApp.class);
        app.run(args);
    }
}

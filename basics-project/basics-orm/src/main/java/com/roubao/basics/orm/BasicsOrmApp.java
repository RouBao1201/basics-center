package com.roubao.basics.orm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/28
 **/
@SpringBootApplication
public class BasicsOrmApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicsOrmApp.class);
        app.run(args);
    }
}

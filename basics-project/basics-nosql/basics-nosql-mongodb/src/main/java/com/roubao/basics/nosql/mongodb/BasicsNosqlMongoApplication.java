package com.roubao.basics.nosql.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Mongodb服务启动类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/29
 **/
@SpringBootApplication
public class BasicsNosqlMongoApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicsNosqlMongoApplication.class);
        app.run(args);
    }
}

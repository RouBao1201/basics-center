package com.roubao.basics.nosql.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Redis服务启动类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/24
 **/
@SpringBootApplication
public class BasicsNosqlRedisApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicsNosqlRedisApplication.class);
        app.run(args);
    }
}

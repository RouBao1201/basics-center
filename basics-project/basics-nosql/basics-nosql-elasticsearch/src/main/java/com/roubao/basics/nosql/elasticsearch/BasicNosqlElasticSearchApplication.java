package com.roubao.basics.nosql.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ES启动类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/24
 **/
@SpringBootApplication
public class BasicNosqlElasticSearchApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicNosqlElasticSearchApplication.class);
        app.run(args);
    }
}

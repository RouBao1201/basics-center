package com.roubao.basics.nosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
@SpringBootApplication
public class BasicsNoSQLApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BasicsNoSQLApp.class);
        app.run(args);
    }
}

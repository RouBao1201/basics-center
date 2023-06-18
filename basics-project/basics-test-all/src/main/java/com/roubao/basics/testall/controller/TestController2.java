package com.roubao.basics.testall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/16
 **/
@RestController
@RequestMapping("/pre")
public class TestController2 extends TestController {

    @Override
    public String getTest01() {
        return "我想看你打篮球！";
    }
}

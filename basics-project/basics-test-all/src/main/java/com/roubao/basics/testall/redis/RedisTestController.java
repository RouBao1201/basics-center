package com.roubao.basics.testall.redis;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roubao.nosql.redis.bean.RedisAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/28
 **/
@RestController
@RequestMapping("/redisTest")
@Slf4j
public class RedisTestController {

    @Resource
    private RedisAdapter redisAdaptor;

    @PostMapping("/set")
    public Object set(@RequestBody RedisSetRequestDTO requestDto) {
        log.info("RedisTestController ==> /set");
        redisAdaptor.set(requestDto.getKey(), requestDto.getValue());
        return redisAdaptor.get(requestDto.getKey());
    }
}

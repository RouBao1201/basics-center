package com.roubao.web.rest.bean;

import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate封装工具
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/3
 **/
public class RestAdapter {

    private final RestTemplate restTemplate;

    public RestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}

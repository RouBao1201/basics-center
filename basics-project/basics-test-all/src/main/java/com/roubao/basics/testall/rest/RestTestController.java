package com.roubao.basics.testall.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.roubao.basics.testall.common.TestRequestDTO;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/15
 **/
@RestController
@RequestMapping("/restTest")
public class RestTestController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/hutoolHttpTest")
    public String hutoolHttpTest(@RequestBody TestRequestDTO testRequestDTO) {
        // String url = "http://127.0.0.1:8091/accessLimit/accessLimitTest";
        String url = "http://10.184.233.80:18020/IOM-CLOUD-SERVICE/orderManagerController/getWorkOrdersByOrderId";
        // Map<String, Object> map = new HashMap<>();
        // map.put("id", testRequestDTO.getId());

        JSONObject entries = new JSONObject();
        entries.putOnce("id", testRequestDTO.getId());

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // String result = HttpUtil.createGet(url).addHeaders(headers).form(map).execute().body();

//        String result = HttpUtil.createGet(url).addHeaders(headers).body(entries.toString()).execute().body();
        String result = HttpUtil.createGet(url).execute().body();

        // HttpRequest httpRequest = HttpRequest.get(url).header("Content-Type", "application/json");
        // String result = httpRequest.execute().body();

        // 请求头
        // HttpHeaders headers2 = new HttpHeaders();
        // headers2.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        //
        // Map<String, Object> params = new HashMap<>();
        // params.put("id", testRequestDTO.getId());
        //
        // HttpEntity<String> entity = new HttpEntity<>(JSONUtil.toJsonStr(params), headers2);
        // ResponseEntity<String> forEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        // String result = forEntity.getBody();
        return "成功:" + result;
    }
}

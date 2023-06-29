package com.roubao.basics.testall.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roubao.basics.testall.dto.UserInfoDTO;
import com.roubao.common.locker.redis.bean.RedisLock;
import com.roubao.common.locker.redis.bean.RedisLocker;
import com.roubao.common.locker.redisson.bean.RedissonLocker;
import com.roubao.web.response.dto.RespResult;
import com.roubao.web.response.unifyresp.UnifySuccResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试API
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/15
 **/
@Api(tags = "测试接口API")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("GET请求测试01")
    @UnifySuccResp
    @GetMapping("/getTest01")
    public String getTest01() {
        return "你想看啥?";
    }

    @ApiOperation("GET请求测试02")
    @UnifySuccResp
    @GetMapping("/getTest02")
    public RespResult<String> getTest02() {
        return RespResult.success("查询成功");
    }

    @ApiOperation("查询用户信息")
    @UnifySuccResp
    @GetMapping("/getUserInfo")
    public RespResult<UserInfoDTO> getUserInfo() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName("RouBao");
        userInfoDTO.setAge(18);
        return RespResult.success(userInfoDTO);
    }
}

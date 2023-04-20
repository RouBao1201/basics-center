package com.roubao.basics.testall.sensitive;

import com.roubao.web.response.unifyresp.UnifyResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/28
 **/
@RestController
@RequestMapping("/sensitiveTest")
@Slf4j
public class SensitiveTestController {

    @GetMapping(value = "/queryUserInfo")
    @UnifyResp
    public UserInfoDTO queryUserInfo() {
        log.info("SensitiveTestController ==> /queryUserInfo ... ");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername("RouBao");
        userInfoDTO.setPassword("123456");
        userInfoDTO.setName("SongRR");
        userInfoDTO.setAge(18);
        return userInfoDTO;
    }
}

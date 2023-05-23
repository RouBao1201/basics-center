package com.roubao.basics.testall.sensitive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roubao.web.response.unifyresp.UnifySuccResp;

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
    @UnifySuccResp
    public UserInfoDTO queryUserInfo() {
        UserInfoDTO dto = new UserInfoDTO();
        log.info("SensitiveTestController ==> /queryUserInfo ... ");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername("RouBao");
        userInfoDTO.setPassword("123456");
        userInfoDTO.setName("SongRR");
        userInfoDTO.setAge(18);
        return userInfoDTO;
    }

    public static void main(String[] args) {
        long time = (24 * 60 * 60 * 1000) + (1000 * 60 * 25) + (1000 * 56);
        System.out.println(formatToHhMmSs(time));
    }

    private static String formatToHhMmSs(long ms) {
        String hoursStr;
        String minutesStr;
        String secondsStr;

        // 时
        long hour = (ms / 1000) / 3600;
        if (hour < 10) {
            hoursStr = "0" + hour;
        }
        else {
            hoursStr = hour + "";
        }
        // 分
        long minute = ((ms / 1000) % 3600) / 60;
        if (minute < 10) {
            minutesStr = "0" + minute;
        }
        else {
            minutesStr = minute + "";
        }
        // 秒
        long second = (ms / 1000) % 60;
        if (second < 10) {
            secondsStr = "0" + second;
        }
        else {
            secondsStr = second + "";
        }
        return hoursStr + ":" + minutesStr + ":" + secondsStr;
    }
}

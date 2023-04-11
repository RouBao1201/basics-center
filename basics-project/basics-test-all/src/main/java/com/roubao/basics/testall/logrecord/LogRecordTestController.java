package com.roubao.basics.testall.logrecord;

import com.roubao.web.common.logrecord.LogRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/11
 **/
@RestController
@RequestMapping("/logRecord")
public class LogRecordTestController {

    @GetMapping("/recordTest")
    @LogRecord(description = "测试记录")
    public String recordTest() {
        return "成功";
    }

    @PostMapping("/customRecordTest")
    @LogRecord(description = "自定义日志记录策略", strategyClass = CustomLogRecordStrategy.class)
    public String customRecordTest(@RequestBody LogRecordTestDTO dto) {
        System.out.println("自定义策略请求体: " + dto);
        return "成功";
    }
}

package com.roubao.basics.testall.logrecord;

import com.roubao.web.common.logrecord.LogRecordDTO;
import com.roubao.web.common.logrecord.LogRecordStrategy;
import org.springframework.stereotype.Service;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/11
 **/
@Service
public class CustomLogRecordStrategy implements LogRecordStrategy<LogRecordTestDTO> {

    @Override
    public void afterRecord(LogRecordDTO<LogRecordTestDTO> recordData) {
        System.out.println("执行自定义日志记录策略");
    }
}

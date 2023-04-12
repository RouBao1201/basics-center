package com.roubao.basics.testall.logrecord;

import org.springframework.stereotype.Service;

import com.roubao.web.common.logrecord.AbstractLogRecordStrategy;
import com.roubao.web.common.logrecord.LogRecordDTO;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/11
 **/
@Service
public class CustomLogRecordAsyncStrategy extends AbstractLogRecordStrategy<LogRecordTestDTO> {
    @Override
    public void afterRecord(LogRecordDTO<LogRecordTestDTO> recordData) {
        try {
            Thread.sleep(8000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("自定义记录日志(异步)");
        System.out.println(recordData);
    }
}

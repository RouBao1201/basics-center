package com.roubao.web.common.logrecord;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认日志记录方法策略实现
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/6
 **/
@Slf4j
public class DefaultLogRecordStrategy implements LogRecordStrategy<Object> {
    @Override
    public void afterRecord(LogRecordDTO<Object> data) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator())
                .append("-------------------------------------------------Before-Default-Log-Record-START-------------------------------------------------").append(System.lineSeparator())
                .append("- URL: ").append(data.getUrl()).append(System.lineSeparator())
                .append("- RequestType: ").append(data.getRequestType()).append(System.lineSeparator())
                .append("- Class: ").append(data.getClass()).append(System.lineSeparator())
                .append("- Method: ").append(data.getMethodName()).append(System.lineSeparator())
                .append("- Param: ").append(data.getParam()).append(System.lineSeparator())
                .append("- Annotation: ").append(data.getAnnotation()).append(System.lineSeparator())
                .append("- Description: ").append(data.getDescription()).append(System.lineSeparator())
                .append("--------------------------------------------------Before-Default-Log-Record-END--------------------------------------------------");
        log.info(sb.toString());
    }
}

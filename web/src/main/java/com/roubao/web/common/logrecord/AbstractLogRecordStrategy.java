package com.roubao.web.common.logrecord;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志记录抽象策略
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/12
 **/
@Slf4j
public abstract class AbstractLogRecordStrategy<T> implements LogRecordStrategy<T> {
    /**
     * 日志记录封装方法
     * 
     * @param recordData
     */
    public void process(LogRecordDTO<T> recordData) {
        try {
            this.afterRecord(recordData);
        }
        catch (Exception e) {
            this.exceptionHandler(recordData, e);
        }
    }

    /**
     * 异常处理
     * 
     * @param recordData recordData
     * @param e Exception
     */
    public void exceptionHandler(LogRecordDTO<T> recordData, Exception e) {
        log.error("LogRecordStrategy Exception. RecordData:{}. ErrorMessage:{}.", recordData, e.getMessage(), e);
    };
}

package com.roubao.web.common.logrecord;

/**
 * 日志记录策略接口
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/6
 **/
public interface LogRecordStrategy<T> {
    /**
     * 记录日志方法
     * 
     * @param recordData recordData
     */
    void record(LogRecordDTO<T> recordData);
}

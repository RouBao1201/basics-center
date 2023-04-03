package com.roubao.basics.testall.orm.mapper;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/29
 **/
public interface BatchSessionMapper {

    int batchInsert(List<T> dataList);
}

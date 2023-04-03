package com.roubao.basics.testall.orm.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/29
 **/
@Data
@ToString
public class DictBatchInsRequestDTO {
    private Integer batchNum;

    private Integer mode;

    private Integer submitNum;
}

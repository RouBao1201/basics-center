package com.roubao.basics.testall.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/29
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DictInsRequestDTO {

    private String dictId;

    private String dictName;
}

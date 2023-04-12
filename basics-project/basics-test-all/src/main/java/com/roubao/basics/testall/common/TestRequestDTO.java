package com.roubao.basics.testall.common;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/12
 **/
@Data
@ToString
public class TestRequestDTO implements Serializable {
    private Integer id;

    private String name;
}

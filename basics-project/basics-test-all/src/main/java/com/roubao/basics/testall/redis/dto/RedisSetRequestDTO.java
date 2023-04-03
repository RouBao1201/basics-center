package com.roubao.basics.testall.redis.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/28
 **/
@Data
@ToString
public class RedisSetRequestDTO implements Serializable {
    private static final long serialVersionUID = 8391884686988019029L;

    private String key;

    private String value;
}

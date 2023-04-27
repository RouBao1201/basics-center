package com.roubao.basics.testall.sensitive;

import com.roubao.common.sensitive.annotation.Desensitize;
import com.roubao.common.sensitive.enums.DesensitizeStrategy;

import lombok.Data;
import lombok.ToString;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/28
 **/
@Data
@ToString
public class UserInfoDTO {
    private static final long serialVersionUID = 9148241281912316356L;

    @Desensitize
    private String username;

    @Desensitize(DesensitizeStrategy.FULL)
    private String password;

    @Desensitize(DesensitizeStrategy.NAME)
    private String name;

    private Integer age;
}

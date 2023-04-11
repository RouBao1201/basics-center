package com.roubao.basics.testall.sensitive;


import com.roubao.common.pojo.dto.BaseDTO;
import com.roubao.common.sensitive.annotation.ISensitive;
import com.roubao.common.sensitive.enums.SensitiveMode;
import lombok.Data;
import lombok.ToString;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/28
 **/
@Data
@ToString
public class UserInfoDTO extends BaseDTO {
    private static final long serialVersionUID = 9148241281912316356L;

    @ISensitive
    private String username;

    @ISensitive(SensitiveMode.FULL)
    private String password;

    @ISensitive(SensitiveMode.NAME)
    private String name;

    private Integer age;
}

package com.roubao.basics.testall.dto;

import com.roubao.common.pojo.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 用户信息DTO
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/15
 **/
@ApiModel("用户信息DTO")
@Data
@ToString
public class UserInfoDTO extends BaseDTO {

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户年龄")
    private Integer age;
}

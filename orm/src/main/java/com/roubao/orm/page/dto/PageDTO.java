package com.roubao.orm.page.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页基础实体
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/16
 **/
@ApiModel("分页请求基础DTO")
public class PageDTO implements Serializable {

    private static final long serialVersionUID = -7668574185556165462L;

    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Integer pageNum;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private Integer pageSize;

    public PageDTO() {
    }

    public PageDTO(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageDTO{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + '}';
    }
}

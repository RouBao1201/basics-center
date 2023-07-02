package com.roubao.orm.page.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回实体
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/22
 **/
@ApiModel("分页响应结果")
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 5098643118417769259L;

    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private int pageNum;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private int pageSize;

    /**
     * 数据总量
     */
    @ApiModelProperty("数据总量")
    private long total;

    /**
     * 结果数据集合
     */
    @ApiModelProperty("结果数据集合")
    private List<T> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResp{" + "pageNum=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + ", list=" + list
                + '}';
    }
}
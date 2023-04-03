package com.roubao.orm.page.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回实体
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/22
 **/
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 5098643118417769259L;

    private int pageNum;

    private int pageSize;

    private long total;

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
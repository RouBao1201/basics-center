package com.roubao.orm.page.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roubao.orm.page.dto.PageDTO;
import com.roubao.orm.page.dto.PageResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 分页查询工具类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/22
 **/
@Slf4j
public final class IPageHelper {

    /**
     * 分页查询
     *
     * @param reqDto 请求体
     * @param select select接口
     * @param clazz 响应类型
     * @param <T> 请求类型枚举
     * @param <R> 响应类型枚举
     * @return PageResult分页通用响应体
     */
    public static <T extends PageDTO, R> PageResult<R> doStart(T reqDto, ISelect select, Class<R> clazz) {
        if (reqDto.getPageNum() == null || reqDto.getPageSize() == null) {
            log.error("IPageHelper ==> The pageNum and pageSize cannot be null.");
            throw new RuntimeException("The pageNum and pageSize cannot be null.");
        }

        // 执行分页查询mapper
        PageInfo<R> pageInfo = PageHelper.startPage(reqDto.getPageNum(), reqDto.getPageSize()).doSelectPageInfo(select);

        // 组装分页通用响应体
        PageResult<R> respResult = new PageResult<>();
        setCommonData(reqDto, respResult);
        respResult.setTotal(pageInfo.getTotal());
        respResult.setList(pageInfo.getList());
        return respResult;
    }

    /**
     * 分页查询（带后置处理）
     *
     * @param reqDto 请求体
     * @param select select接口
     * @param clazz 响应类型
     * @param consumer 查询结果后置处理
     * @param <T> 请求类型枚举
     * @param <R> 响应类型枚举
     * @return PageResult分页通用响应体
     */
    public static <T extends PageDTO, R> PageResult<R> doStartAfter(T reqDto, ISelect select, Class<R> clazz,
        Consumer<List<R>> consumer) {
        PageResult<R> pageResult = doStart(reqDto, select, clazz);
        consumer.accept(pageResult.getList());
        // 重新赋值总数,避免在后置操作中对数据进行修改
        pageResult.setTotal(pageResult.getList().size());
        return pageResult;
    }

    /**
     * 获取空分页对象
     *
     * @param reqDto 请求体
     * @param clazz 响应体类型
     * @param <T> 请求体枚举
     * @param <R> 响应体枚举
     * @return PageResult
     */
    public static <T extends PageDTO, R> PageResult<R> doEmpty(T reqDto, Class<R> clazz) {
        PageResult<R> respResult = new PageResult<>();
        setCommonData(reqDto, respResult);
        respResult.setTotal(0);
        respResult.setList(new ArrayList<>(0));
        return respResult;
    }

    /**
     * 创建分页校验对象
     *
     * @return PageJudge
     */
    public static PageJudge buildJudge() {
        return new PageJudge(true);
    }

    private static <T extends PageDTO, R> void setCommonData(T reqDto, PageResult<R> respResult) {
        respResult.setPageNum(reqDto.getPageNum());
        respResult.setPageSize(reqDto.getPageSize());
    }

    /**
     * 分页查询条件限制类
     */
    public static class PageJudge {
        private boolean conditionResult;

        public PageJudge(boolean conditionResult) {
            this.conditionResult = conditionResult;
        }

        /**
         * 限制条件（true才执行分页查询,false返回空分页对象）
         *
         * @param condition 判断条件
         * @return PageJudge
         */
        public PageJudge condition(boolean condition) {
            if (this.conditionResult) {
                this.conditionResult = condition;
            }
            return this;
        }

        /**
         * 执行分页查询（限制条件为true执行SQL查询,false返回空分页对象）
         *
         * @param reqDto 请求体
         * @param select 查询接口
         * @param clazz 响应体类型
         * @param <T> 请求体枚举
         * @param <R> 响应体枚举
         * @return PageResult
         */
        public <T extends PageDTO, R> PageResult<R> doStart(T reqDto, ISelect select, Class<R> clazz) {
            if (this.conditionResult) {
                return IPageHelper.doStart(reqDto, select, clazz);
            }
            else {
                return IPageHelper.doEmpty(reqDto, clazz);
            }
        }

        /**
         * 分页查询（带后置处理）
         *
         * @param reqDto 请求体
         * @param select select接口
         * @param clazz 响应类型
         * @param consumer 查询结果后置处理
         * @param <T> 请求类型枚举
         * @param <R> 响应类型枚举
         * @return PageResult分页通用响应体
         */
        public <T extends PageDTO, R> PageResult<R> doStartAfter(T reqDto, ISelect select, Class<R> clazz,
            Consumer<List<R>> consumer) {
            if (this.conditionResult) {
                PageResult<R> pageResult = IPageHelper.doStart(reqDto, select, clazz);
                consumer.accept(pageResult.getList());
                return pageResult;
            }
            else {
                return IPageHelper.doEmpty(reqDto, clazz);
            }
        }
    }

    private IPageHelper() {

    }
}
package com.roubao.orm.jdbc.bean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * JdbcTemplate封装工具
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/28
 **/
@Slf4j
public class JdbcAdapter {

    /**
     * 分段提交默认数量
     */
    private static final int DEFAULT_SUBMIT_SIZE = 500;

    private final JdbcTemplate jdbcTemplate;

    public JdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public Long queryCount(String sql) {
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public <T> T queryForObject(String sql, Class<T> objClass, Object... args) {
        return jdbcTemplate.queryForObject(sql, objClass, args);
    }

    public List<Map<String,Object>> queryForList(String sql, Object... args) {
        return jdbcTemplate.queryForList(sql, args);
    }

    public <T> List<T> queryForList(String sql, Class<T> objClass, Object... args) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(objClass), args);
    }

    public Map<String, Object> queryForMap(String sql) {
        return jdbcTemplate.queryForMap(sql);
    }

    /**
     * JdbcTemplate批量插入【分段提交】
     *
     * @param sql        sql
     * @param dataList   数据集合
     * @param submitSize 批量提交数量
     * @param consumer   参数设置回调
     * @param <T>        数据类型枚举
     */
    public <T> void batchInsert(String sql, List<T> dataList, int submitSize,
                                DiInjectConsumer<PreparedStatement, T> consumer) {
        batchUpdate(this.jdbcTemplate, sql, dataList, submitSize, consumer);
    }

    /**
     * JdbcTemplate批量插入【分段提交】
     *
     * @param sql      sql
     * @param dataList 数据集合
     * @param consumer 参数设置回调
     * @param <T>      数据类型枚举
     */
    public <T> void batchInsert(String sql, List<T> dataList, DiInjectConsumer<PreparedStatement, T> consumer) {
        batchUpdate(this.jdbcTemplate, sql, dataList, DEFAULT_SUBMIT_SIZE, consumer);
    }

    /**
     * 批量修改【分段提交】
     *
     * @param mapperFunc mapper方法
     * @param list       插入的数据集合
     * @param size       一次提交的数量
     * @param <T>        数据枚举
     */
    public <T> void batchInsert(Consumer<List<T>> mapperFunc, List<T> list, int size) {
        batchUpdate(mapperFunc, list, size);
    }

    /**
     * 批量修改（有返回值）【分段提交】
     *
     * @param mapperFunc mapper方法
     * @param list       插入的数据集合
     * @param size       一次提交的数量
     * @param <T>        数据枚举
     */
    public <T> int batchInsert(Function<List<T>, Integer> mapperFunc, List<T> list, int size) {
        return batchUpdate(mapperFunc, list, size);
    }

    /**
     * 批量插入【分段提交】
     *
     * @param mapperFunc mapper方法
     * @param list       插入的数据集合
     * @param <T>        数据枚举
     */
    public <T> void batchInsert(Consumer<List<T>> mapperFunc, List<T> list) {
        batchUpdate(mapperFunc, list, DEFAULT_SUBMIT_SIZE);
    }

    /**
     * 批量插入（有返回值）【分段提交】
     *
     * @param mapperFunc mapper方法
     * @param list       插入的数据集合
     * @param <T>        数据枚举
     */
    public <T> int batchInsert(Function<List<T>, Integer> mapperFunc, List<T> list) {
        return batchUpdate(mapperFunc, list, DEFAULT_SUBMIT_SIZE);
    }

    /**
     * JdbcTemplate批量修改【分段提交】
     *
     * @param sql      sql
     * @param dataList 数据集合
     * @param consumer 参数设置回调
     * @param <T>      数据类型枚举
     */
    public <T> void batchUpdate(String sql, List<T> dataList, int submitSize,
                                DiInjectConsumer<PreparedStatement, T> consumer) {
        batchUpdate(this.jdbcTemplate, sql, dataList, submitSize, consumer);
    }

    /**
     * JdbcTemplate批量修改【分段提交】
     *
     * @param sql      sql
     * @param dataList 数据集合
     * @param consumer 参数设置回调
     * @param <T>      数据类型枚举
     */
    public <T> void batchUpdate(String sql, List<T> dataList, DiInjectConsumer<PreparedStatement, T> consumer) {
        batchUpdate(this.jdbcTemplate, sql, dataList, DEFAULT_SUBMIT_SIZE, consumer);
    }

    /**
     * 批量修改【分段提交】
     *
     * @param mapperFunc 插入的数据集合
     * @param list       插入的数据集合
     * @param <T>        数据枚举
     */
    public <T> void batchUpdate(Consumer<List<T>> mapperFunc, List<T> list) {
        batchUpdate(mapperFunc, list, DEFAULT_SUBMIT_SIZE);
    }

    /**
     * 批量修改（有返回值）【分段提交】
     *
     * @param mapperFunc 插入的数据集合
     * @param list       插入的数据集合
     * @param <T>        数据枚举
     */
    public <T> int batchUpdate(Function<List<T>, Integer> mapperFunc, List<T> list) {
        return batchUpdate(mapperFunc, list, DEFAULT_SUBMIT_SIZE);
    }

    /**
     * JdbcTemplate批量修改【分段提交】
     *
     * @param jdbcTemplate jdbcTemplate
     * @param sql          sql
     * @param dataList     数据集合
     * @param submitSize 提交数量
     * @param consumer     参数设置回调
     * @param <T>          数据枚举类型
     */
    public <T> void batchUpdate(JdbcTemplate jdbcTemplate, String sql, List<T> dataList, int submitSize,
                                DiInjectConsumer<PreparedStatement, T> consumer) {
        int count = dataList.size() % submitSize == 0 ? (dataList.size() / submitSize) : (dataList.size() / submitSize + 1);
        for (int i = 1; i <= count; i++) {
            int from = Math.min((i - 1) * submitSize, dataList.size());
            int to = Math.min((i) * submitSize, dataList.size());
            batchExecute(jdbcTemplate, sql, dataList.subList(from, to), consumer);
        }
    }

    /**
     * 批量插入【分段提交】
     *
     * @param mapperFunc mapper方法
     * @param dataList   插入的数据集合
     * @param submitSize 一次提交的数量
     * @param <T>        数据枚举
     */
    public <T> void batchUpdate(Consumer<List<T>> mapperFunc, List<T> dataList, int submitSize) {
        int count = dataList.size() % submitSize == 0 ? (dataList.size() / submitSize) : (dataList.size() / submitSize + 1);
        for (int i = 1; i <= count; i++) {
            int from = Math.min((i - 1) * submitSize, dataList.size());
            int to = Math.min(i * submitSize, dataList.size());
            // 执行mapper方法
            mapperFunc.accept(dataList.subList(from, to));
        }
    }

    /**
     * 批量修改（有返回值）【分段提交】
     *
     * @param mapperFunc mapper方法
     * @param dataList   插入的数据集合
     * @param submitSize 一次提交的数量
     * @param <T>        数据枚举
     */
    public <T> int batchUpdate(Function<List<T>, Integer> mapperFunc, List<T> dataList, int submitSize) {
        int updateSize = 0;
        int count = dataList.size() % submitSize == 0 ? (dataList.size() / submitSize) : (dataList.size() / submitSize + 1);
        for (int i = 1; i <= count; i++) {
            int from = Math.min((i - 1) * submitSize, dataList.size());
            int to = Math.min(i * submitSize, dataList.size());
            // 执行mapper方法
            updateSize += mapperFunc.apply(dataList.subList(from, to));
        }
        return updateSize;
    }

    private <T> void batchExecute(JdbcTemplate jdbcTemplate, String sql, List<T> subList,
                                  DiInjectConsumer<PreparedStatement, T> consumer) {
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            // SQL参数设置回调
            @Override
            public void setValues(PreparedStatement preparedStatement, int index) throws SQLException {
                consumer.accept(preparedStatement, subList.get(index));
            }

            // 设置提交数量
            @Override
            public int getBatchSize() {
                return subList.size();
            }
        });
    }

    @FunctionalInterface
    public interface DiInjectConsumer<P, T> {
        /**
         * 回调方法
         *
         * @param p PreparedStatement
         * @param t 提交的数据
         * @throws SQLException 异常
         */
        void accept(P p, T t) throws SQLException;
    }
}

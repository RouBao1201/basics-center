package com.roubao.orm.jdbc.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * mybatis封装适配工具
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/6/10
 **/
@Slf4j
public class MybatisAdapter {

    private static final int DEFAULT_SUBMIT_SIZE = 500;

    private final SqlSessionFactory sqlSessionFactory;

    public MybatisAdapter(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 批量插入（mybatis方式）
     *
     * @param mapperClass mapper方法（具有返回值int）
     * @param dataList    数据集合
     * @param mapperFunc  mapper方法
     * @param <T>         数据反省
     * @return 成功修改的数据量
     */
    public <T, K> int batchUpdate(Class<K> mapperClass, List<T> dataList, DiInjectConsumer<K, T, Integer> mapperFunc) {
        return batchUpdate(mapperClass, dataList, mapperFunc, DEFAULT_SUBMIT_SIZE);
    }

    /**
     * 批量插入（mybatis方式）
     *
     * @param mapperClass mapper方法（具有返回值int）
     * @param dataList    数据集合
     * @param mapperFunc  mapper方法
     * @param commitSize  一次提交的事务数据量
     * @param <T>         数据反省
     * @return 成功修改的数据量
     */
    public <T, K> int batchUpdate(Class<K> mapperClass, List<T> dataList, DiInjectConsumer<K, T, Integer> mapperFunc, int commitSize) {
        int updateSize = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            K mapper = sqlSession.getMapper(mapperClass);
            for (int j = 0; j < dataList.size(); j++) {
                Integer apply = mapperFunc.accept(mapper, dataList.get(j));
                updateSize += apply;
                if (j != 0 && j % commitSize == 0) {
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
            sqlSession.commit();
            sqlSession.clearCache();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return updateSize;
    }

    @FunctionalInterface
    public interface DiInjectConsumer<P, T, R> {
        /**
         * 回调方法
         *
         * @param p 参数1
         * @param t 参数2
         * @return 相应结果
         */
        R accept(P p, T t);
    }
}

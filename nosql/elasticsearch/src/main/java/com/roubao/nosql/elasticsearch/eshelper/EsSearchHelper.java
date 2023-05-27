package com.roubao.nosql.elasticsearch.eshelper;

import com.roubao.nosql.elasticsearch.enums.AOEnum;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ElasticSearch查询助手
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/25
 **/
@Slf4j
public class EsSearchHelper {

    private final String indexName;

    private final SearchRequest searchRequest;

    private final SearchSourceBuilder searchSourceBuilder;

    private final BoolQueryBuilder baseBoolQuerybuilder;

    private final RestHighLevelClient restHighLevelClient;

    private static final ConcurrentHashMap<String, BoolQueryBuilder> childBoolQueryBuilderMap = new ConcurrentHashMap<>();

    private static BoolQueryBuilder atPresentBoolQueryBuilder = null;

    private static String atPresentBoolQueryKey = null;

    public EsSearchHelper(String indexName, RestHighLevelClient restHighLevelClient) {
        this.indexName = indexName;
        this.searchRequest = new SearchRequest(indexName);
        this.searchSourceBuilder = new SearchSourceBuilder();
        // 如果不将trackTotalHits设置为true，查询总数只能是10000条
        this.searchSourceBuilder.trackTotalHits(true);
        this.restHighLevelClient = restHighLevelClient;
        this.baseBoolQuerybuilder = QueryBuilders.boolQuery();
    }

    /**
     * 查询全部
     *
     * @return EsSearchHelper
     */
    public EsSearchHelper matchAllQuery() {
        setCondition(AOEnum.MUST, QueryBuilders.matchAllQuery());
        return this;
    }

    /**
     * MUST 精确查询（相当于sql中的=）
     *
     * @param name  字段名
     * @param value 字段值
     * @return EsSearchHelper
     */
    public EsSearchHelper mustTermQuery(String name, Object value) {
        termQuery(name, value, AOEnum.MUST);
        return this;
    }

    /**
     * SHOULD 精确查询（相当于sql中的=）
     *
     * @param name  字段名
     * @param value 字段值
     * @return EsSearchHelper
     */
    public EsSearchHelper shouldTermQuery(String name, Object value) {
        termQuery(name, value, AOEnum.SHOULD);
        return this;
    }

    /**
     * MUST 范围查询
     *
     * @param name 字段名
     * @param from 最小值
     * @param to   最大值
     * @return EsSearchHelper
     */
    public EsSearchHelper mustRangeQuery(String name, Object from, Object to) {
        rangeQuery(name, from, to, AOEnum.MUST);
        return this;
    }

    /**
     * SHOULD 范围查询
     *
     * @param name 字段名
     * @param from 最小值
     * @param to   最大值
     * @return EsSearchHelper
     */
    public EsSearchHelper shouldRangeQuery(String name, Object from, Object to) {
        rangeQuery(name, from, to, AOEnum.SHOULD);
        return this;
    }

    /**
     * MUST 模糊查询（非分词）
     *
     * @param name      字段名
     * @param likeValue 模糊值
     * @return EsSearchHelper
     */
    public EsSearchHelper mustFuzzyQuery(String name, Object likeValue) {
        fuzzyQuery(name, likeValue, AOEnum.MUST);
        return this;
    }

    /**
     * SHOULD 模糊查询（非分词）
     *
     * @param name      字段名
     * @param likeValue 模糊值
     * @return EsSearchHelper
     */
    public EsSearchHelper shouldFuzzyQuery(String name, Object likeValue) {
        fuzzyQuery(name, likeValue, AOEnum.SHOULD);
        return this;
    }

    /**
     * 分页
     *
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     * @return EsSearchHelper
     */
    public EsSearchHelper page(Integer pageNum, Integer pageSize) {
        searchSourceBuilder.from((pageNum - 1) * pageSize).size(pageSize);
        return this;
    }

    /**
     * 排序
     *
     * @param name      排序字段
     * @param sortOrder 排序形式
     * @return EsSearchHelper
     */
    public EsSearchHelper orderBy(String name, SortOrder sortOrder) {
        searchSourceBuilder.sort(name, sortOrder);
        return this;
    }

    /**
     * 需要展示的字段
     *
     * @param includes 字段名集合
     * @return EsSearchHelper
     */
    public EsSearchHelper showFiled(String... includes) {
        searchSourceBuilder.fetchSource(includes, new String[]{});
        return this;
    }

    /**
     * 不想展示的字段
     *
     * @param excludes 字段名集合
     * @return EsSearchHelper
     */
    public EsSearchHelper notShowFiled(String... excludes) {
        searchSourceBuilder.fetchSource(new String[]{}, excludes);
        return this;
    }

    /**
     * 精确匹配查询
     *
     * @param name   字段名称
     * @param value  匹配值
     * @param aoEnum 条件连接类型枚举
     * @return EsSearchHelper
     */
    public EsSearchHelper termQuery(String name, Object value, AOEnum aoEnum) {
        setCondition(aoEnum, QueryBuilders.termQuery(name, value));
        return this;
    }

    /**
     * 范围查询
     *
     * @param name   字段名称
     * @param from   最小值
     * @param to     最大值
     * @param aoEnum 条件连接类型枚举
     * @return EsSearchHelper
     */
    public EsSearchHelper rangeQuery(String name, Object from, Object to, AOEnum aoEnum) {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(name);
        rangeQueryBuilder.from(from).to(to);
        setCondition(aoEnum, rangeQueryBuilder);
        return this;
    }

    /**
     * 模糊查询
     *
     * @param name      字段名
     * @param likeValue 模糊匹配数据
     * @param aoEnum    条件连接类型枚举
     * @return EsSearchHelper
     */
    public EsSearchHelper fuzzyQuery(String name, Object likeValue, AOEnum aoEnum) {
        setCondition(aoEnum, QueryBuilders.fuzzyQuery(name, likeValue));
        return this;
    }

    /**
     * MUST 开始内部bool查询条件
     *
     * @return EsSearchHelper
     */
    public EsSearchHelper openMust() {
        return open(AOEnum.MUST);
    }

    /**
     * SHOULD 开始内部bool查询条件
     *
     * @return EsSearchHelper
     */
    public EsSearchHelper openShould() {
        return open(AOEnum.SHOULD);
    }

    /**
     * 开始内部查询条件
     *
     * @param aoEnum 条件连接类型枚举
     * @return EsSearchHelper
     */
    public EsSearchHelper open(AOEnum aoEnum) {
        if (atPresentBoolQueryKey != null || atPresentBoolQueryBuilder != null) {
            throw new RuntimeException("The previous subconstraint never call close method. Please check.");
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        atPresentBoolQueryBuilder = boolQueryBuilder;
        if (aoEnum == AOEnum.MUST) {
            atPresentBoolQueryKey = AOEnum.MUST.toString() + UUID.randomUUID();
        } else {
            atPresentBoolQueryKey = AOEnum.SHOULD.toString() + UUID.randomUUID();
        }
        childBoolQueryBuilderMap.put(atPresentBoolQueryKey, boolQueryBuilder);
        return this;
    }

    /**
     * 关闭内部bool查询条件
     *
     * @return EsSearchHelper
     */
    public EsSearchHelper close() {
        if (atPresentBoolQueryKey == null || atPresentBoolQueryBuilder == null) {
            throw new RuntimeException("The previous subconstraint never call open method. Please check.");
        }
        // 判断内部查询条件整体是MUST还是SHOULD
        if (atPresentBoolQueryKey.startsWith(AOEnum.MUST.toString())) {
            baseBoolQuerybuilder.must(childBoolQueryBuilderMap.get(atPresentBoolQueryKey));
        } else {
            baseBoolQuerybuilder.should(childBoolQueryBuilderMap.get(atPresentBoolQueryKey));
        }
        atPresentBoolQueryBuilder = null;
        atPresentBoolQueryKey = null;
        return this;
    }

    /**
     * 查询最终执行方法
     *
     * @return SearchResponse
     */
    public SearchResponse doStartSearch() {
        if (atPresentBoolQueryKey != null || atPresentBoolQueryBuilder != null) {
            throw new RuntimeException("The previous subconstraint never call close method. Please check.");
        }
        searchSourceBuilder.query(baseBoolQuerybuilder);
        searchRequest.source(searchSourceBuilder);
        log.info("Search DSL:[{}].", searchSourceBuilder);
        try {
            return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("EsSearchHelper ==> ElasticSearch doStartSearch error.", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置查询条件
     *
     * @param aoEnum       条件连接类型枚举
     * @param queryBuilder 查询builder
     */
    private void setCondition(AOEnum aoEnum, QueryBuilder queryBuilder) {
        BoolQueryBuilder boolQueryBuilder = baseBoolQuerybuilder;
        // 判断当前是否处于拼接内部查询条件中
        if (atPresentBoolQueryKey != null) {
            boolQueryBuilder = childBoolQueryBuilderMap.get(atPresentBoolQueryKey);
        }
        switch (aoEnum) {
            case SHOULD:
                boolQueryBuilder.should(queryBuilder);
                break;
            case MUST:
                boolQueryBuilder.must(queryBuilder);
            default:
                break;
        }
    }
}

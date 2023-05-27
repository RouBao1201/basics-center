package com.roubao.nosql.elasticsearch.bean;

import com.roubao.nosql.elasticsearch.eshelper.EsSearchHelper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

import java.io.IOException;

/**
 * ES操作适配器（封装ES常见操作）
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/24
 **/
@Slf4j
public class ElasticSearchAdapter {

    private final RestHighLevelClient restHighLevelClient;

    public ElasticSearchAdapter(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public RestHighLevelClient getRestHighLevelClient() {
        return restHighLevelClient;
    }

    /**
     * 创建索引
     *
     * @param indexName 索引名称
     * @return boolean
     */
    public boolean createIndex(String indexName) {
        CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
        try {
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
            return createIndexResponse.isAcknowledged();
        } catch (IOException e) {
            log.error("Create Index[{}] error.", indexName, e);
            return false;
        }
    }

    /**
     * 创建索引（不存在才创建）
     *
     * @param indexName indexName
     * @return boolean
     */
    public boolean createIndexIfAbsent(String indexName) {
        if (existIndex(indexName)) {
            log.info("This index[{}] is exist. No need to duplicate.", indexName);
            return true;
        }
        return createIndex(indexName);
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名称
     * @return boolean
     */
    public boolean deleteIndex(String indexName) {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        try {
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            return delete.isAcknowledged();
        } catch (IOException e) {
            log.error("Check existIndex[{}] error.", indexName, e);
            return false;
        }
    }

    /**
     * 索引是否存在
     *
     * @param indexName 索引名称
     * @return boolean
     */
    public boolean existIndex(String indexName) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        try {
            return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("Check existIndex[{}] error.", indexName, e);
            return false;
        }
    }

    /**
     * 查询
     *
     * @param indexName 索引名称
     * @return EsSearchHelper
     */
    public EsSearchHelper boolSearch(String indexName) {
        return new EsSearchHelper(indexName, restHighLevelClient);
    }
}

package com.roubao.nosql.elasticsearch.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ElasticSearch配置
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/23
 **/
@Data
@ToString
@ConfigurationProperties(prefix = "elasticsearch.configuration")
public class ElasticSearchProperties {
    /**
     * host ip 地址（集群）
     */
    private String hosts = "127.0.0.1:9200";

    /**
     * 用户名
     */
    private String username = "elastic";

    /**
     * 密码
     */
    private String password = "elastic";

    /**
     * 请求方式
     */
    private String scheme = "http";

    /**
     * es集群名称
     */
    private String clusterName = "roubao-cluster";

    /**
     * 连接超时时间
     */
    private int connectTimeOut = 5000;

    /**
     * socket 连接超时时间
     */
    private int socketTimeOut = 30000;

    /**
     * 请求超时时间
     */
    private int connectionRequestTimeOut = 5000;

    /**
     * 最大连接数
     */
    private int maxConnTotal = 100;

    /**
     * 每个路由的最大连接数
     */
    private int maxConnPerRoute = 100;
}

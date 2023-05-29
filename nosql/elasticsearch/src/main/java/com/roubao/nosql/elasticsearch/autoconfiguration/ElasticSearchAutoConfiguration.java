package com.roubao.nosql.elasticsearch.autoconfiguration;

import com.roubao.nosql.elasticsearch.bean.ElasticSearchAdapter;
import com.roubao.nosql.elasticsearch.properties.ElasticSearchProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * ElasticSearch自动装配类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/23
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
public class ElasticSearchAutoConfiguration {

    private static final String BANNER_I_ELASTICSEARCH =
            "   ___     ___  _     ___  ___  _____  ___   ___  ___  ___  ___  ___   ___  _  _ " + System.lineSeparator() +
            "  |_ _|   | __|| |   /   \\/ __||_   _||_ _| / __|/ __|| __|/   \\| _ \\ / __|| || |" + System.lineSeparator() +
            "   | |    | _| | |__ | - |\\__ \\  | |   | | | (__ \\__ \\| _| | - ||   /| (__ | __ |" + System.lineSeparator() +
            "  |___|   |___||____||_|_||___/  |_|  |___| \\___||___/|___||_|_||_|_\\ \\___||_||_|";

    private final ElasticSearchProperties elasticSearchProperties;

    public ElasticSearchAutoConfiguration(ElasticSearchProperties elasticSearchProperties) {
        this.elasticSearchProperties = elasticSearchProperties;
    }

    @Bean("restHighLevelClient")
    @ConditionalOnMissingBean(RestHighLevelClient.class)
    public RestHighLevelClient restHighLevelClient() {
        log.info(System.lineSeparator() + BANNER_I_ELASTICSEARCH + System.lineSeparator());
        log.info("ElasticSearchAutoConfiguration ==> Start custom autoConfiguration [RestHighLevelClient] bean.");
        log.info("ElasticSearchAutoConfiguration ==> ElasticSearchProperties: {}.", elasticSearchProperties);
        // 拆分地址
        String hosts = elasticSearchProperties.getHosts();
        List<HttpHost> hostLists = new ArrayList<>();
        String[] hostList = hosts.split(",");
        for (String addr : hostList) {
            String host = addr.split(":")[0];
            String port = addr.split(":")[1];
            hostLists.add(new HttpHost(host, Integer.parseInt(port), elasticSearchProperties.getScheme()));
        }
        // 转换成 HttpHost 数组
        HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});

        // 构建连接对象
        RestClientBuilder builder = RestClient.builder(httpHost);

        // 设置用户名、密码
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(elasticSearchProperties.getUsername(), elasticSearchProperties.getPassword()));

        // 连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(elasticSearchProperties.getConnectTimeOut());
            requestConfigBuilder.setSocketTimeout(elasticSearchProperties.getSocketTimeOut());
            requestConfigBuilder.setConnectionRequestTimeout(elasticSearchProperties.getConnectionRequestTimeOut());
            return requestConfigBuilder;
        });

        // 连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(elasticSearchProperties.getMaxConnTotal());
            httpClientBuilder.setMaxConnPerRoute(elasticSearchProperties.getMaxConnPerRoute());
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }

    @Bean("elasticSearchAdapter")
    @ConditionalOnMissingBean(ElasticSearchAdapter.class)
    public ElasticSearchAdapter elasticSearchAdapter() {
        return new ElasticSearchAdapter(restHighLevelClient());
    }
}

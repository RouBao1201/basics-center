package com.roubao.orm.jdbc.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.roubao.orm.jdbc.bean.TransactionHandler;

/**
 * 事务助手配置类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/28
 **/
@Configuration
public class TransactionHandlerAutoConfiguration {

    @Bean
    @ConditionalOnBean(DataSourceTransactionManager.class)
    @ConditionalOnMissingBean(TransactionHandler.class)
    public TransactionHandler transactionUtils(DataSourceTransactionManager dataSourceTransaction) {
        return new TransactionHandler(dataSourceTransaction);
    }
}

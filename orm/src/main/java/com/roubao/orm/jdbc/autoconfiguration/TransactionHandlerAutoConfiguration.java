package com.roubao.orm.jdbc.autoconfiguration;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Configuration
public class TransactionHandlerAutoConfiguration {

    @Bean("transactionHandler")
    @ConditionalOnMissingBean(TransactionHandler.class)
    public TransactionHandler transactionHandler(DataSourceTransactionManager dataSourceTransaction) {
        log.info("TransactionHandlerAutoConfiguration ==> Start custom autoConfiguration [TransactionHandler] bean.");
        return new TransactionHandler(dataSourceTransaction);
    }
}

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

    private static final String BANNER_I_TRANSACTION_HANDLER =
            "   ___     _____  ___  ___  _  _  ___  ___   ___  _____  ___   ___   _  _     _  _  ___  _  _  ___   _     ___  ___   " + System.lineSeparator() +
            "  |_ _|   |_   _|| _ \\/   \\| \\| |/ __|/   \\ / __||_   _||_ _| / _ \\ | \\| |   | || |/   \\| \\| ||   \\ | |   | __|| _ \\" + System.lineSeparator() +
            "   | |      | |  |   /| - || .` |\\__ \\| - || (__   | |   | | | (_) || .` |   | __ || - || .` || |) || |__ | _| |   /" + System.lineSeparator() +
            "  |___|     |_|  |_|_\\|_|_||_|\\_||___/|_|_| \\___|  |_|  |___| \\___/ |_|\\_|   |_||_||_|_||_|\\_||___/ |____||___||_|_\\";

    @Bean("transactionHandler")
    @ConditionalOnMissingBean(TransactionHandler.class)
    public TransactionHandler transactionHandler(DataSourceTransactionManager dataSourceTransaction) {
        log.info(System.lineSeparator() + BANNER_I_TRANSACTION_HANDLER + System.lineSeparator());
        log.info("TransactionHandlerAutoConfiguration ==> Start custom autoConfiguration [TransactionHandler] bean.");
        return new TransactionHandler(dataSourceTransaction);
    }
}

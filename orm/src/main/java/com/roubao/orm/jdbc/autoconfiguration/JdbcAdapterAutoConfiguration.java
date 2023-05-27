package com.roubao.orm.jdbc.autoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.roubao.orm.jdbc.bean.JdbcAdapter;

/**
 * JdbcAdaptor工具配置类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/28
 **/
@Slf4j
@Configuration
public class JdbcAdapterAutoConfiguration {

    private static final String BANNER_I_JDBC_ADAPTER =
            "   ___        _  ___   ___   ___     ___  ___   ___  ___  _____  ___  ___  " + System.lineSeparator() +
            "  |_ _|    _ | ||   \\ | _ ) / __|   /   \\|   \\ /   \\| _ \\|_   _|| __|| _ \\ " + System.lineSeparator() +
            "   | |    | || || |) || _ \\| (__    | - || |) || - ||  _/  | |  | _| |   / " + System.lineSeparator() +
            "  |___|    \\__/ |___/ |___/ \\___|   |_|_||___/ |_|_||_|    |_|  |___||_|_\\ ";

    @Bean
    @ConditionalOnMissingBean(JdbcAdapter.class)
    public JdbcAdapter jdbcAdaptor(JdbcTemplate jdbcTemplate) {
        log.info(System.lineSeparator() + BANNER_I_JDBC_ADAPTER+ System.lineSeparator());
        log.info("JdbcAdapterAutoConfiguration ==> Start custom autoConfiguration [JdbcAdapter] bean.");
        return new JdbcAdapter(jdbcTemplate);
    }
}

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
    @Bean("jdbcAdapter")
    @ConditionalOnMissingBean(JdbcAdapter.class)
    public JdbcAdapter jdbcAdapter(JdbcTemplate jdbcTemplate) {
        log.info("JdbcAdapterAutoConfiguration ==> Start custom autoConfiguration [JdbcAdapter] bean.");
        return new JdbcAdapter(jdbcTemplate);
    }
}

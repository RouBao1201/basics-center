package com.roubao.orm.jdbc.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
@Configuration
public class JdbcAdapterAutoConfiguration {

    @Bean
    @ConditionalOnBean(JdbcTemplate.class)
    @ConditionalOnMissingBean(JdbcAdapter.class)
    public JdbcAdapter jdbcAdaptor(JdbcTemplate jdbcTemplate) {
        return new JdbcAdapter(jdbcTemplate);
    }
}

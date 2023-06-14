package com.roubao.orm.jdbc.autoconfiguration;

import com.roubao.orm.jdbc.bean.MybatisAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisAdaptor工具配置类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/28
 **/
@Slf4j
@Configuration
public class MybatisAdapterAutoConfiguration {

    @Bean("mybatisAdapter")
    @ConditionalOnMissingBean(MybatisAdapter.class)
    public MybatisAdapter jdbcAdapter(SqlSessionFactory sqlSessionFactory) {
        log.info("MybatisAdapterAutoConfiguration ==> Start custom autoConfiguration [MybatisAdapter] bean.");
        return new MybatisAdapter(sqlSessionFactory);
    }
}

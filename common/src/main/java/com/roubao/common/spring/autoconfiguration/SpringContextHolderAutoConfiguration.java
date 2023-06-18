package com.roubao.common.spring.autoconfiguration;

import com.roubao.common.spring.bean.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring上下文工具自动装配
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/26
 **/
@Slf4j
@Configuration
public class SpringContextHolderAutoConfiguration {

    @Bean("springContextHolder")
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        log.info("SpringContextHolderAutoConfiguration ==> Start custom autoConfiguration [SpringContextHolder] bean.");
        return new SpringContextHolder();
    }
}

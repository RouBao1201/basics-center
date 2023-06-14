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

    private static final String BANNER_I_SPRINGCONTEXT_HOLDER =
            "   ___      ___  ___  ___  ___  _  _   ___   ___   ___   _  _  _____  ___ __  __ _____     _  _   ___   _     ___   ___  ___   " + System.lineSeparator() +
            "  |_ _|    / __|| _ \\| _ \\|_ _|| \\| | / __| / __| / _ \\ | \\| ||_   _|| __|\\ \\/ /|_   _|   | || | / _ \\ | |   |   \\ | __|| _ \\  " + System.lineSeparator() +
            "   | |     \\__ \\|  _/|   / | | | .` || (_ || (__ | (_) || .` |  | |  | _|  >  <   | |     | __ || (_) || |__ | |) || _| |   /  " + System.lineSeparator() +
            "  |___|    |___/|_|  |_|_\\|___||_|\\_| \\___| \\___| \\___/ |_|\\_|  |_|  |___|/_/\\_\\  |_|     |_||_| \\___/ |____||___/ |___||_|_\\ ";

    @Bean("springContextHolder")
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        log.info(System.lineSeparator() + BANNER_I_SPRINGCONTEXT_HOLDER + System.lineSeparator());
        log.info("SpringContextHolderAutoConfiguration ==> Start custom autoConfiguration [SpringContextHolder] bean.");
        return new SpringContextHolder();
    }
}

package com.roubao.web.response.autoconfiguration;

import com.roubao.web.response.config.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 全局异常自动装配
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/4
 **/
@Configuration
@Import(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerAutoConfiguration {
}

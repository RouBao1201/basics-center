package com.roubao.web.common.logrecord;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 日志记录切面自动装配
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/6
 **/
@Configuration
@Import(LogRecordAspect.class)
public class LogRecordAspectAutoConfiguration {
}

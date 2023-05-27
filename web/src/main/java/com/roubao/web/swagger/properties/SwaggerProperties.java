package com.roubao.web.swagger.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/27
 **/
@Data
@ToString
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    /**
     * 是否开启
     */
    private Boolean enable = true;

    /**
     * 标题
     */
    private String title = "webApiTitle";

    /**
     * 版本号
     */
    private String version = "1.0.0";

    /**
     * 描述
     */
    private String description = "webApi";

    /**
     * 分组名
     */
    private String groupName = "webApiGroup";

    /**
     * 访问前缀
     */
    private String pathMapping;

    /**
     * 协议支持
     */
    private String protocols = "http,https";

    /**
     * 服务条款
     */
    private String termsOfServiceUrl;
}

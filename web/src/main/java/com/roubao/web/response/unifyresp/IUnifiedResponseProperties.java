package com.roubao.web.response.unifyresp;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;

/**
 * 统一响应配置类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/20
 **/
@ConfigurationProperties(prefix = "unify-response")
public class IUnifiedResponseProperties {

    /**
     * 执行模式（手动/自动）
     */
    private EnableCustomUnifiedSuccessResponse.RunMode runMode = EnableCustomUnifiedSuccessResponse.RunMode.AUTO;

    /**
     * 包含以下字符的URI不进行统一响应处理
     */
    private String[] excludeUriContains = new String[] {
        "swagger", "api-docs"
    };

    public EnableCustomUnifiedSuccessResponse.RunMode getRunMode() {
        return runMode;
    }

    public void setRunMode(EnableCustomUnifiedSuccessResponse.RunMode runMode) {
        this.runMode = runMode;
    }

    public String[] getExcludeUriContains() {
        return excludeUriContains;
    }

    public void setExcludeUriContains(String[] excludeUriContains) {
        this.excludeUriContains = excludeUriContains;
    }

    @Override
    public String toString() {
        return "IUnifiedResponseProperties{" + "runMode=" + runMode + ", excludeUriContains="
            + Arrays.toString(excludeUriContains) + '}';
    }
}

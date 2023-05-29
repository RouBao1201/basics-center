package com.roubao.web.response.unifyresp;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 统一响应配置类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/20
 **/
@ConfigurationProperties(prefix = "unify-response")
public class IUnifiedResponseProperties {

    private EnableCustomUnifiedSuccResponse.RunMode runMode = EnableCustomUnifiedSuccResponse.RunMode.AUTO;

    public EnableCustomUnifiedSuccResponse.RunMode getRunMode() {
        return runMode;
    }

    public void setRunMode(EnableCustomUnifiedSuccResponse.RunMode runMode) {
        this.runMode = runMode;
    }

    @Override
    public String toString() {
        return "UnifiedResponseProperties{" + "runMode=" + runMode + '}';
    }
}

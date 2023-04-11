package com.roubao.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/5
 **/
@ConfigurationProperties(prefix = "security.aes")
public class AesProperties {
    private String key = "roubao";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "AesProperties{" +
                "key='" + key + '\'' +
                '}';
    }
}

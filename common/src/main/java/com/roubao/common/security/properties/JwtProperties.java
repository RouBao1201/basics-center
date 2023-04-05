package com.roubao.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jwt配置类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/9
 **/
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey = "roubao";

    private Integer expireTime = 1000;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "JwtProperties{" +
                "secretKey='" + secretKey + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}

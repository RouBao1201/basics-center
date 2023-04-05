package com.roubao.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/5
 **/
@ConfigurationProperties(prefix = "md5")
public class Md5Properties {
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Md5Properties{" +
                "salt='" + salt + '\'' +
                '}';
    }
}

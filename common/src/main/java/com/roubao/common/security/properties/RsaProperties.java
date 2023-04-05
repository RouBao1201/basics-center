package com.roubao.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Rsa配置
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/5
 **/
@ConfigurationProperties(prefix = "rsa")
public class RsaProperties {
    private String privateKey = "roubao";

    private String publicKey = "roubao";

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        return "RsaProperties{" +
                "privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                '}';
    }
}

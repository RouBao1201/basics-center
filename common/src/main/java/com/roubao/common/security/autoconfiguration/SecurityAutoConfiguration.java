package com.roubao.common.security.autoconfiguration;

import com.roubao.common.security.bean.AESUtil;
import com.roubao.common.security.bean.JwtUtil;
import com.roubao.common.security.bean.MD5Util;
import com.roubao.common.security.bean.RSAUtil;
import com.roubao.common.security.properties.AesProperties;
import com.roubao.common.security.properties.JwtProperties;
import com.roubao.common.security.properties.Md5Properties;
import com.roubao.common.security.properties.RsaProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 安全工具自动装配
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/5
 **/
@Configuration
@EnableConfigurationProperties({AesProperties.class, Md5Properties.class, RsaProperties.class, JwtProperties.class})
public class SecurityAutoConfiguration {
    private final AesProperties aesProperties;

    private final RsaProperties rsaProperties;

    private final Md5Properties md5Properties;

    private final JwtProperties jwtProperties;

    public SecurityAutoConfiguration(AesProperties aesProperties, RsaProperties rsaProperties, Md5Properties md5Properties, JwtProperties jwtProperties) {
        this.aesProperties = aesProperties;
        this.rsaProperties = rsaProperties;
        this.md5Properties = md5Properties;
        this.jwtProperties = jwtProperties;
    }

    @Bean("md5Util")
    @ConditionalOnMissingBean(MD5Util.class)
    public MD5Util md5Util() {
        return new MD5Util(md5Properties);
    }

    @Bean("rsaUtil")
    @ConditionalOnMissingBean(RSAUtil.class)
    public RSAUtil rsaUtil() {
        return new RSAUtil(rsaProperties);
    }

    @Bean("aesUtil")
    @ConditionalOnMissingBean(AESUtil.class)
    public AESUtil aesUtil() {
        return new AESUtil(aesProperties);
    }

    @Bean("jwtUtil")
    @ConditionalOnMissingBean(AESUtil.class)
    public JwtUtil jwtUtil() {
        return new JwtUtil(jwtProperties);
    }
}

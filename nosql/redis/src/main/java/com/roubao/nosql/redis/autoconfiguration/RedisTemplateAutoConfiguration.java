package com.roubao.nosql.redis.autoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/11/24
 */
@Slf4j
@Order(1)
@Configuration
public class RedisTemplateAutoConfiguration {

    /**
     * redis序列化配置（Jackson2JsonRedisSerializer）
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisTemplate
     */
    // @Bean("redisTemplate")
    // @ConditionalOnMissingBean(RedisTemplate.class)
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    // RedisTemplate<String, Object> template = new RedisTemplate<>();
    // template.setConnectionFactory(redisConnectionFactory);
    // // String的序列化方式
    // StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    // // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
    // Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
    // Object.class);
    // // key序列化方式采用String类型
    // template.setKeySerializer(stringRedisSerializer);
    // // value序列化方式采用jackson类型
    // template.setValueSerializer(jackson2JsonRedisSerializer);
    // // hash的key序列化方式也是采用String类型
    // template.setHashKeySerializer(stringRedisSerializer);
    // // hash的value也是采用jackson类型
    // template.setHashValueSerializer(jackson2JsonRedisSerializer);
    // template.afterPropertiesSet();
    // return template;
    // }

    /**
     * redis序列化配置（GenericJackson2JsonRedisSerializer）
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisTemplate
     */
    @Bean("redisTemplate")
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("RedisTemplateAutoConfiguration ==> Start custom autoConfiguration [RedisTemplate] bean.");
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // String的序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 使用GenericJackson2JsonRedisSerializer 替换默认序列化(默认采用的是JDK序列化)
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // key序列化方式采用String类型
        template.setKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson类型
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        // hash的key序列化方式也是采用String类型
        template.setHashKeySerializer(stringRedisSerializer);
        // hash的value也是采用jackson类型
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 序列化配置（fastjson）
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisTemplate
     */
    // @Bean("redisTemplate")
    // @ConditionalOnMissingBean(RedisTemplate.class)
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    // RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    // redisTemplate.setConnectionFactory(redisConnectionFactory);
    // FastJson2JsonRedisSerializer<Object> fastJsonSerializer = new FastJson2JsonRedisSerializer<>(Object.class);
    //
    // redisTemplate.setKeySerializer(new StringRedisSerializer());
    // redisTemplate.setValueSerializer(fastJsonSerializer);
    //
    // redisTemplate.setHashKeySerializer(fastJsonSerializer);
    // redisTemplate.setHashValueSerializer(fastJsonSerializer);
    //
    // redisTemplate.afterPropertiesSet();
    // return redisTemplate;
    // }
}

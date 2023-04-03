package com.roubao.common.utils;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

/**
 * Jackson封装工具
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/3
 **/
@Slf4j
public class JacksonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        // 设置允许序列化空的实体类（否则会抛出异常）
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 设置 把java.util.Date, Calendar输出为数字（时间戳）
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                // 设置在遇到未知属性的时候不抛出异常
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // 强制JSON 空字符串("")转换为null对象值
                // .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                // 设置数字丢失精度问题
                .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                // 设置没有引号的字段名
                .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
                // 设置允许单引号
                .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
                // 设置接受只有一个元素的数组的反序列化
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    /**
     * bean转json
     *
     * @param obj obj
     * @return String
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("JacksonHelper ==> BeanToJson happen JsonProcessingException. {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * jsonToBean
     *
     * @param jsonStr jsonStr
     * @param clazz   clazz
     * @param <T>     <T>
     * @return T
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            log.error("JacksonHelper ==> JsonToBean happen JsonProcessingException. {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * jsonToMap
     *
     * @param jsonStr jsonStr
     * @return Map<String, Object>
     */
    public static Map<String, Object> jsonToMap(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("JacksonHelper ==> JsonToMap happen JsonProcessingException. {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * jsonToList
     *
     * @param jsonStr jsonStr
     * @param clazz   clazz
     * @param <T>     <T>
     * @return List<T>
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            log.error("JacksonHelper ==> JsonToList happen JsonProcessingException. {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * jsonToList
     *
     * @param jsonStr jsonStr
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> jsonToList(String jsonStr) {
        JavaType mapJavaType = objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, mapJavaType);
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            log.error("JacksonHelper ==> JsonToList happen JsonProcessingException. {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private JacksonUtil() {

    }
}

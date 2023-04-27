package com.roubao.common.sensitive.config;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.roubao.common.sensitive.annotation.Desensitize;
import com.roubao.common.sensitive.enums.DesensitizeStrategy;
import com.roubao.common.sensitive.utils.DesensitizeUtil;

/**
 * 序列化配置类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class DesensitizeJsonSerializerConfig extends JsonSerializer<String> implements ContextualSerializer {
    private DesensitizeStrategy strategy;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DesensitizeUtil.desensitize(this.strategy, value));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {
        Desensitize annotation = property.getAnnotation(Desensitize.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass())) {
            this.strategy = annotation.value();
            return this;
        }
        return prov.findValueSerializer(property.getType(), property);
    }
}
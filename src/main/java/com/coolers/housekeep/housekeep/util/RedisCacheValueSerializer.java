package com.coolers.housekeep.housekeep.util;

import com.coolers.housekeep.housekeep.constant.FormatConst;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RedisCacheValueSerializer extends GenericJackson2JsonRedisSerializer {
    public RedisCacheValueSerializer() {
        super();
    }

    @Override
    public Object deserialize(byte[] source) throws SerializationException {
        Object value = super.deserialize(source);
        if (Method.isNotEmptyObject(value) && value instanceof String str) {
            if (str.length() == 10) {
                return LocalDate.parse(str, DateTimeFormatter.ofPattern(FormatConst.yyyy_MM_dd));
            }
            if (str.length() == 19) {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(FormatConst.yyyy_MM_dd_space_HH_mm_ss));
            }
        }
        return value;
    }

    @Override
    public byte[] serialize(Object source) throws SerializationException {
        if (source instanceof LocalDate) {
            return super.serialize(((LocalDate) source).format(DateTimeFormatter.ofPattern(FormatConst.yyyy_MM_dd)));
        }
        if (source instanceof LocalDateTime) {
            return super.serialize(((LocalDateTime) source).format(DateTimeFormatter.ofPattern(FormatConst.yyyy_MM_dd_space_HH_mm_ss)));
        }
        return super.serialize(source);
    }
}


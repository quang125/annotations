package com.os.annotation.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.os.annotation.test.annotations.NamingStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author QuangNN
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.setPropertyNamingStrategy(new NamingStrategy());
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonProcessingException {
        return MAPPER.readValue(json, classOfT);
    }
    public static String toJson(Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }
}
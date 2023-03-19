package com.zebs.facturation.commun.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebs.facturation.article.model.entity.Article;

import static org.aspectj.bridge.MessageUtil.fail;

public class ObjectConverter {

    public static String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }

    public static <T> T stringToEntity(String jsonString, Class<T> valueType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, valueType);
    }
}
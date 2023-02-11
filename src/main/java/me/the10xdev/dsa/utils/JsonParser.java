package me.the10xdev.dsa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode parse(String input) throws JsonProcessingException {
        return mapper.readTree(input);
    }

}

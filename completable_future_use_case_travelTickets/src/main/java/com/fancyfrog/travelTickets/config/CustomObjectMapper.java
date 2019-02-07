package com.fancyfrog.travelTickets.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        super();
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        enable(SerializationFeature.INDENT_OUTPUT);
        registerModule(new JavaTimeModule());
    }
}

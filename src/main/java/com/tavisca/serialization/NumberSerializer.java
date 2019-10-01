package com.tavisca.serialization;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.model.Number;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class NumberSerializer implements Serializer<Number> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberSerializer.class);

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Number number) {
        byte[] serializedNumber = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            serializedNumber = mapper.writeValueAsString(number).getBytes();
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to Serialize Number {}, due to {}", number.toString(), e.getMessage());
            e.printStackTrace();
        }
        return serializedNumber;
    }

    @Override
    public void close() {

    }
}

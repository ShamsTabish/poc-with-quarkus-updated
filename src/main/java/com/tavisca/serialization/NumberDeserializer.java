package com.tavisca.serialization;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.model.Number;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class NumberDeserializer implements Deserializer<Number> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberDeserializer.class);

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Number deserialize(String s, byte[] bytes) {
        ObjectMapper mapper=new ObjectMapper();
        Number number= null;
        try {
            number = mapper.readValue(bytes, Number.class);
        } catch (IOException e) {
            LOGGER.error("Failed to deserialize number {}, due to {}",s, e.getMessage());
            e.printStackTrace();
        }
        return number;
    }

    @Override
    public void close() {

    }
}

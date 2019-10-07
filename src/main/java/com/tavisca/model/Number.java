package com.tavisca.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tavisca.serialization.LocalDateTimeDeserializer;
import com.tavisca.serialization.LocalDateTimeSerializer;
import io.quarkus.arc.DefaultBean;
import lombok.Data;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@ApplicationScoped
public class Number {
    Integer number;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime timestamp;

    public Number() {

    }

    public Number(Integer number) {
        this.number = number;
        timestamp = LocalDateTime.now();
    }

    public Number(LocalDateTime timestamp, Integer number) {
        this.timestamp = timestamp;
        this.number = number;
    }

    public Number increment() {
        return new Number(this.timestamp, this.number + 1);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

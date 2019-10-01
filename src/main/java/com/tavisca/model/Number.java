package com.tavisca.model;

import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.ToString;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonValue;
import java.time.LocalDateTime;

@Data
@ApplicationScoped
public class Number {
    final private Integer number;
    final private java.time.LocalDateTime timestamp;

    public Number(Integer number){
        this.number=number;
        timestamp=LocalDateTime.now();
    }

    private Number(LocalDateTime timestamp, Integer number){
        this.timestamp=timestamp;
        this.number=number;
    }

    public Number increment(){
        return new Number(this.timestamp, this.number+1);
    }

}

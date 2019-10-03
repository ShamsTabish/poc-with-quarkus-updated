package com.tavisca.model;

import io.quarkus.arc.DefaultBean;
import lombok.Data;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@Data
@ApplicationScoped
public class Number {
    Integer number;
    LocalDateTime timestamp;

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

}

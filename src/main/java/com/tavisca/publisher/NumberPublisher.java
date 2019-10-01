package com.tavisca.publisher;

import com.tavisca.model.Number;
import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.annotations.Stream;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NumberPublisher {
    private final Number number;

    public NumberPublisher(Number number) {
        this.number = number;
    }

    @Outgoing("number")
    @Broadcast
    @Stream("number")
    public Flowable<Number> publishNumber() {
        System.out.println("-------------------------------------------------------");
        System.out.println("Publishing .. " + number);
        System.out.println("-------------------------------------------------------");
        return Flowable.just(number);
    }
}

package com.tavisca.publisher;

import com.tavisca.model.Number;
import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NumberPublisher {

//    public Number number;
//
//    public NumberPublisher(Number number) {
//        this.number = number;
//    }

    @Outgoing("number")
    @Broadcast
//    @Stream("number")
    public Flowable<Integer> publishNumber() {
        System.out.println("-------------------------------------------------------");
//        System.out.println("Publishing .. " + number);
        System.out.println("-------------------------------------------------------");
        return Flowable.just(23);
    }
}

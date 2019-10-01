package com.tavisca.listener;


import com.tavisca.model.Number;
import com.tavisca.persistence.OfferRepository;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NumberListener {

    @Incoming("number")
    public void receiveNumber(Number number) {
        System.out.println("Received: "+number);
    }

}

package com.tavisca.listener;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.persistence.OfferRepository;
import com.tavisca.model.Offer;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.time.OffsetDateTime;

@ApplicationScoped
public class OfferListener {

    @Inject
    private OfferRepository offerRepository;


    @Incoming("new-offer")
    public void receiveMessage(String message){
        processMessage(message);
    }

    private void processMessage(String message) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(message);
            Offer offer = prepareOffer(jsonNode);
            System.out.println("Received Offer From kafka: "+offer);
            offerRepository.saveOffer(offer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Offer prepareOffer(JsonNode jsonNode) {
        return new Offer(jsonNode.get("offer_key").asLong(),
                OffsetDateTime.parse(jsonNode.get("started_on").asText()),
                OffsetDateTime.parse(jsonNode.get("expires_on").asText()),
                jsonNode.get("title").asText(),
                "created");
    }
}

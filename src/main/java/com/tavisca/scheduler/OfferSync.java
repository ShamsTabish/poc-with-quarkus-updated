package com.tavisca.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.tavisca.model.Offer;
import com.tavisca.restClient.RestHelper;
import io.quarkus.scheduler.Scheduled;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class OfferSync {

    private final String ACCESS_URL="https://offer-demo.adcrws.com/v1/offers?member_key=123";

    @Inject
    private RestHelper restHelper;

    @Scheduled(every = "60s")
    public void fetchAndPushOffer(){
        System.out.println("Hi from scheduler");
        MultivaluedMap<String, Object> headers=new MultivaluedHashMap<>();
        headers.add("Access-Token","bb2679194d4736d9e1f8244df473d87fcabd815aaf3d3d5fda8c2673e7a8c0de");
        headers.add("Content-Type",MediaType.APPLICATION_JSON);

        Offer invoke = (Offer) restHelper.invoke(ACCESS_URL, headers, Offer.class);
        System.out.println(invoke);
    }
}

package com.tavisca.resource;

import com.tavisca.model.Number;
import com.tavisca.publisher.NumberPublisher;
import io.smallrye.reactive.messaging.annotations.Stream;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/number")
public class NumberStoreResource {

    @Inject
    private NumberPublisher numberPublisher;

    @POST @Path("/{input}")
    @Produces(MediaType.APPLICATION_JSON)
    public Number publishNumber(@PathParam("input") Integer input) {
        final Number number = new Number(input);
        numberPublisher.add(number);
        return number;
    }


    @GET
    public List<Number> getNumbers() {
        return Arrays.asList(new Number(1));
    }

}
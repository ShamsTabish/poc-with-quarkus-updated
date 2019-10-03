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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NumberStoreResource {

//    @Inject
//    @Stream("number")
//    Publisher<Number> publisher;

    @POST @Path("/{input}")
    public Number publishNumber(@PathParam("input") Integer input) {
        final Number number = new Number(input);
        NumberPublisher numberPublisher=new NumberPublisher();
        numberPublisher.publishNumber();
        return number;
    }

//    @GET
//    @Path("/stream")
//    public Publisher<Number> getPublisherStream() {
//        return publisher;
//    }

    @GET
    public List<Number> getNumbers() {
        return Arrays.asList(new Number(1));
    }

}
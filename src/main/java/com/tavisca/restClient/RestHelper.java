package com.tavisca.restClient;

import com.tavisca.model.Offer;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class RestHelper{

    private static final Logger LOGGER=LoggerFactory.getLogger(RestHelper.class);

    public <T>T invoke(final String uri,final MultivaluedMap<String, Object> requestHeaders,final Class<T> response) {
        Client client = ResteasyClientBuilder.newClient();
        try {
            return (T) client
                    .target(uri)
                    .request()
                    .headers(requestHeaders)
                    .get(response);
        } catch (ClientErrorException e) {
            LOGGER.error(e.getMessage());
            throw e;
        } finally {
            client.close();
        }

    }
}

package com.tavisca.publisher;

import com.tavisca.model.Number;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.util.concurrent.*;

@Singleton
public class NumberPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberPublisher.class);

    private Executor executor = Executors.newSingleThreadExecutor();
    private BlockingQueue<Number> messages = new LinkedBlockingQueue<>();

    public void add(Number message) {
        messages.add(message);
    }

    @Outgoing("number")
    public CompletionStage<Message<Number>> publishNumber() {
        return CompletableFuture.supplyAsync(() -> {
            Number message;
            try {
                message = messages.take();
            } catch (InterruptedException e) {
                LOGGER.error("Failed to fetch message from end-point due to {}", e);
                throw new RuntimeException(e);
            }
            return Message.of(message);
        }, executor);
    }
}

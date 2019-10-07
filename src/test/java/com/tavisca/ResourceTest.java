package com.tavisca;

import com.tavisca.model.Number;
import io.quarkus.test.junit.QuarkusTest;
import net.manub.embeddedkafka.EmbeddedKafka;
import net.manub.embeddedkafka.EmbeddedKafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;
import scala.collection.JavaConverters$;
import scala.collection.immutable.Map;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testKafkaPublish() throws Exception{
        EmbeddedKafkaConfig embeddedKafkaConfig = new EmbeddedKafkaConfigImp();
        EmbeddedKafka.start(embeddedKafkaConfig);
        given().when().post("/number/56").then().statusCode(200);
        Thread.sleep(10000);
        Number res = getConsumedRecord();
        Assert.assertTrue(res.getNumber() == 56);
        EmbeddedKafka.stop();
    }

    private Number getConsumedRecord() {
        Properties props = getProperties();
        KafkaConsumer<String, Number> consumer = new KafkaConsumer<>(props);
        String topic = "number";
        consumer.subscribe(Arrays.asList(topic));
        Number res = new Number(0);
        while (true) {
            ConsumerRecords<String, Number> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, Number> record : records) {
                System.out.println(record.value());
                res = record.value();
                break;
            }
            if(res.getNumber() != 0)
                break;
        }
        return res;
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "gp-1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.tavisca.serialization.NumberDeserializer");
        return props;
    }
}

class EmbeddedKafkaConfigImp implements EmbeddedKafkaConfig {

    @Override
    public int kafkaPort() {
        return 9092;
    }

    @Override
    public int zooKeeperPort() {
        return 2181;
    }

    @Override
    public Map<String, String> customBrokerProperties() {
        HashMap<String, String> map = new HashMap();
        map.put("replica.fetch.max.bytes", "2000000");
        map.put("message.max.bytes", "2000000");
        return JavaConverters$.MODULE$.mapAsScalaMapConverter(map).asScala()
                .toMap(scala.Predef$.MODULE$.<scala.Tuple2<String, String>>conforms());
    }

    @Override
    public Map<String, String> customProducerProperties() {
        HashMap<String, String> map = new HashMap();
        map.put("max.request.size", "2000000");
        return JavaConverters$.MODULE$.mapAsScalaMapConverter(map).asScala()
                .toMap(scala.Predef$.MODULE$.<scala.Tuple2<String, String>>conforms());
    }

    @Override
    public Map<String, String> customConsumerProperties() {
        HashMap<String, String> map = new HashMap();
        map.put("max.partition.fetch.bytes", "2000000");
        return JavaConverters$.MODULE$.mapAsScalaMapConverter(map).asScala()
                .toMap(scala.Predef$.MODULE$.<scala.Tuple2<String, String>>conforms());
    }

    @Override
    public int numberOfThreads() {
        return 1;
    }
}
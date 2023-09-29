package com.example.kafkademo.messaging;

import com.example.kafkademo.DataInitializer;
import com.example.kafkademo.messaging.config.KafkaTopicConfig;
import com.example.kafkademo.messaging.model.MeasurementEvent;
import com.example.kafkademo.repository.entity.VehicleType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class MeasurementProducer {

    private Logger logger = Logger.getLogger(String.valueOf(MeasurementProducer.class));

    private final KafkaTopicConfig kafkaTopicConfig;
    private final KafkaTemplate<Long, MeasurementEvent> kafkaTemplate;

    MeasurementProducer(
            KafkaTopicConfig kafkaTopicConfig,
            KafkaTemplate<Long, MeasurementEvent> kafkaTemplate
    ) {
        this.kafkaTopicConfig = kafkaTopicConfig;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void produce() {
        logger.info("sleeping...");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException ie) {
            // do nothing
        }
        var event = new MeasurementEvent(4711L, VehicleType.PKW, LocalDateTime.now());

        logger.info("sending event:\n" + event);
        kafkaTemplate.send(kafkaTopicConfig.MEASUREMENT_TOPIC, event);
        logger.info("event sended");
    }
}

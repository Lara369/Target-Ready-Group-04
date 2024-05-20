package com.targetindia.EcomStreaming.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import com.targetindia.EcomStreaming.entites.Orders;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    
    public KafkaTemplate<String, Orders> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Orders> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Orders data) {
        LOGGER.info(String.format("message sent --> %s", data.toString()));
        Message<Orders> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "Orders")
                .build();

        kafkaTemplate.send(message);
    }

}
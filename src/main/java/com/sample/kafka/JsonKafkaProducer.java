package com.sample.kafka;

import com.sample.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JsonKafkaProducer {

    @Autowired
    private KafkaTemplate<String, Account> kafkaTemplate1;

    public void sendMessage(String topic, Account data) {
        log.info("Message sent -> {} from topic: {}", data.toString(), topic);

        Message<Account> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate1.send(message);
    }
}

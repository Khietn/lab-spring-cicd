package com.sample.kafka;

import com.sample.constants.KafkaTopic;
import com.sample.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = KafkaTopic.TOPIC_JAVAGUIDE,groupId = "myGroupId")
    public void consume(String message) {
        log.info("Message received: {} from topic: {}", message, KafkaTopic.TOPIC_JAVAGUIDE);
    }

    @KafkaListener(topics = KafkaTopic.TOPIC_GUIDE_JSON,groupId = "myGroupId")
    public void consume(Account message) {
        log.info("Message received: {} from topic: {}", message.toString(), KafkaTopic.TOPIC_GUIDE_JSON);
    }
}

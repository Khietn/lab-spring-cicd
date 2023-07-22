package com.sample.controller;

import com.sample.constants.KafkaTopic;
import com.sample.kafka.JsonKafkaProducer;
import com.sample.kafka.KafkaProducer;
import com.sample.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {
    @Autowired
    private KafkaProducer kakfaProducer;

    @Autowired
    private JsonKafkaProducer jsonKafkaProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam ("message")String message) {
        kakfaProducer.sendMessage(KafkaTopic.TOPIC_JAVAGUIDE,message);
        return ResponseEntity.ok("Message sent to the topic");
    }

    @PostMapping("/publishJson")
    public ResponseEntity<String> publicJson(@RequestBody Account account) {
        log.info(account.toString());
        jsonKafkaProducer.sendMessage(KafkaTopic.TOPIC_GUIDE_JSON, account);
        return ResponseEntity.ok("Json account sent");
    }

}

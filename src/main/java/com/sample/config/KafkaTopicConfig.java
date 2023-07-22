package com.sample.config;

import com.sample.constants.KafkaTopic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public NewTopic javaGuidesTopic() {
        return TopicBuilder.name(KafkaTopic.TOPIC_JAVAGUIDE).build();
    }

    public NewTopic guideJson() {
        return TopicBuilder.name(KafkaTopic.TOPIC_GUIDE_JSON).build();
    }
}

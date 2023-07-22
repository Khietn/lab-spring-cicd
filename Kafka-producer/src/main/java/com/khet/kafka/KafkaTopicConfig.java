package com.khet.kafka;

import com.khet.constants.KafkaTopic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    public NewTopic javaGuidesTopic() {
        return TopicBuilder.name(KafkaTopic.TOPIC_RECENT_CHANGED).build();
    }
}

package com.smnk107.linkedIn.posts_service.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.post-created-topic}")
    String postCreatedTopic;

    @Value("${kafka.topic.post-liked-topic}")
    String postLikedTopic;
    @Bean
    public NewTopic getPostCreatedTopic()
    {
        return new NewTopic(postCreatedTopic,3,(short)1);
    }

    @Bean
    public NewTopic getPostLikedTopic()
    {
        return new NewTopic(postLikedTopic,3,(short)1);
    }
}

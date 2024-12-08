package com.smnk107.linkedIn.connections_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.request-sent-topic}")
    String requestSentTopic;
    @Value("${kafka.topic.request-received-topic}")
    String requestReceivedTopic;
    @Value("${kafka.topic.request-accepted-topic}")
    String requestAcceptTopic;
    @Value("${kafka.topic.request-rejected-topic}")
    String requestRejectTopic;
}

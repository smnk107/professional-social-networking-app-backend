package com.smnk107.linkedIn.connections_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfig {

    @Bean
    ModelMapper giveModelmapper()
    {
        return new ModelMapper();
    }
}

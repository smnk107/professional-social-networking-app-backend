package com.smnk107.linkedIn.posts_service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    UserInterceptor userInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        log.info("Adding interceptor in registry !!");
        registry.addInterceptor(userInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

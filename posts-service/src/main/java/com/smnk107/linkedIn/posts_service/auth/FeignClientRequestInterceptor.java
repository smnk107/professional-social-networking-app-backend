package com.smnk107.linkedIn.posts_service.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header("X-userId",UserContextHolder.getCurrentUserid().toString());
    }
}

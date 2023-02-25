package com.yanghi.study.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import javax.annotation.Resource;

@Configuration
public class WebConfiguration {

    @Resource
    private OAuth2ClientContext context;

    @Bean
    @LoadBalanced
    public OAuth2RestTemplate restTemplate(){
        return new OAuth2RestTemplate(new ClientCredentialsResourceDetails(), context);
    }
}
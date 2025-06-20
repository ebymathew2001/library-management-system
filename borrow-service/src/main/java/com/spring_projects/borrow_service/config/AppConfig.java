package com.spring_projects.borrow_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        org.springframework.http.client.HttpComponentsClientHttpRequestFactory requestFactory =
                new org.springframework.http.client.HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        return restTemplate;


    }
}

package com.spring_project.book_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class MapperConfig {



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();

    }
}

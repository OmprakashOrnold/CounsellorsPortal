package com.dsoft.portal.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        // Configure mapping strategy
        mapper.getConfiguration().setMatchingStrategy( MatchingStrategies.STRICT);
        return mapper;
    }
}
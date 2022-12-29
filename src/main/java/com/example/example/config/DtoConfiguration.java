package com.example.example.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}

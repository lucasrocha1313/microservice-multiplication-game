package br.com.tonim.multiplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class BeanConfig {
    @Bean
    public Random beanRandom(){
        return new Random();
    }
}

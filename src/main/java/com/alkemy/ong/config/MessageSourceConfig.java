package com.alkemy.ong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean
    public ResourceBundleMessageSource messageSource(){

        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("locale/messages");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
}

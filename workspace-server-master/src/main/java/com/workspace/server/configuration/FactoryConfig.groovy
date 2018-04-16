package com.workspace.server.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor



/**
 * Created by CHENMA11 on 4/12/2018.
 */
@Configuration
@EnableAutoConfiguration
class FactoryConfig {
    final static Logger logger= LoggerFactory.getLogger(FactoryConfig.class);

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }

}

package io.github.maodua.wrench.pager.config;

import io.github.maodua.wrench.pager.handler.IResultHandler;
import io.github.maodua.wrench.pager.handler.SchResultHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagerTestConfig {

    @Bean
    public IResultHandler resultHandler(){
        return new SchResultHandler();
    }

}
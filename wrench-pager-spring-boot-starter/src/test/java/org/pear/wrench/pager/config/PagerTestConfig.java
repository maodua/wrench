package org.pear.wrench.pager.config;

import org.pear.wrench.pager.handler.IResultHandler;
import org.pear.wrench.pager.handler.SchResultHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagerTestConfig {

    @Bean
    public IResultHandler resultHandler(){
        return new SchResultHandler();
    }

}

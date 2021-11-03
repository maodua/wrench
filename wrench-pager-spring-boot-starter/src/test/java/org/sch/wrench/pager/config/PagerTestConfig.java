package org.sch.wrench.pager.config;

import org.sch.wrench.pager.handler.DefaultResultHandler;
import org.sch.wrench.pager.handler.IResultHandler;
import org.sch.wrench.pager.handler.SchResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagerTestConfig {

    @Bean
    public IResultHandler resultHandler(){
        return new SchResultHandler();
    }

}

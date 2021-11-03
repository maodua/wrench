package com.github.maodua.wrench.pager.autoconfigure;

import com.github.maodua.wrench.pager.handler.DefaultResultHandler;
import com.github.maodua.wrench.pager.handler.IResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WrenchPagerConfig {
    @Bean
    @ConditionalOnMissingBean(IResultHandler.class)
    public IResultHandler defaultResultHandler(){
        return new DefaultResultHandler();
    }
}

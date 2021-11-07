package io.github.maodua.wrench.pager.config;

import io.github.maodua.wrench.pager.handler.IPageDataHandler;
import io.github.maodua.wrench.pager.handler.IResultHandler;
import io.github.maodua.wrench.pager.handler.CustomizePageDataHandler;
import io.github.maodua.wrench.pager.handler.RResultHandler;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagerTestConfig {

//    @Bean
    public IResultHandler resultHandler(){
        return new RResultHandler();
    }

//    @Bean
    public IPageDataHandler resultDataHandler(){
        return new CustomizePageDataHandler();
    }
}

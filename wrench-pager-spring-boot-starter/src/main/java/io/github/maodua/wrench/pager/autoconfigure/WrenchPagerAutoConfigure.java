package io.github.maodua.wrench.pager.autoconfigure;

import io.github.maodua.wrench.pager.handler.DefaultPageDataHandler;
import io.github.maodua.wrench.pager.handler.DefaultResultHandler;
import io.github.maodua.wrench.pager.handler.IPageDataHandler;
import io.github.maodua.wrench.pager.handler.IResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WrenchPagerAutoConfigure {
    @Bean
    @ConditionalOnMissingBean(IResultHandler.class)
    public DefaultResultHandler defaultResultHandler(){
        return new DefaultResultHandler();
    }

    @Bean
    @ConditionalOnMissingBean(IPageDataHandler.class)
    public DefaultPageDataHandler defaultResultDataHandler(){
        return new DefaultPageDataHandler();
    }
}

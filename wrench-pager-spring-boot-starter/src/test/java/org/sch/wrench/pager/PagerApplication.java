package org.sch.wrench.pager;

import org.sch.wrench.common.util.Springs;
import org.sch.wrench.pager.handler.DefaultResultHandler;
import org.sch.wrench.pager.handler.IResultHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class PagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PagerApplication.class, args);
        Map<String, IResultHandler> beans = Springs.getBeans(IResultHandler.class);
        System.out.println(beans);
    }


}

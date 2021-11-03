package org.pear.wrench.pager;

import org.pear.wrench.common.util.Springs;
import org.pear.wrench.pager.handler.IResultHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class PagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PagerApplication.class, args);
        Map<String, IResultHandler> beans = Springs.getBeans(IResultHandler.class);
        System.out.println(beans);
    }


}

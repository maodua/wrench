package io.github.maodua.wrench.pager;

import io.github.maodua.wrench.common.util.Springs;
import io.github.maodua.wrench.pager.handler.IResultHandler;
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

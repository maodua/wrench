package io.github.maodua.wrench.pager.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("wrench.pager")
@Data
public class WrenchPagerConfigure {
    /**
     * 当前页
     */
    private String page = "page";
    /**
     * 页大小
     */
    private String pageSize = "pageSize";

}

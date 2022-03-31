package io.github.maodua.wrench.file.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Paths;

/**
 * 文件池模块的配置.
 */
@Data
@ConfigurationProperties("wrench.file")
public class FileStoreConfigure {
    /**
     * 文件的根目录
     */
    private String rootPath = Paths.get(System.getProperty("user.dir"), "file-store").toString();
    /**
     * 是否替换URL前缀
     */
    private Boolean urlReplace = false;
    /**
     * 第三方网站的指定前缀
     */
    private String urlPrefix = "";
}

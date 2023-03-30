package io.github.maodua.wrench.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "wrench.auth")
public class AuthConfigProperties {
    /**
     * 开放的URL
     * KEY: 请求方法, 如 GET、POST、PUT...
     * VAL: 实际的URL, 可使用 AntPathMatcher 方式匹配验证
     */
    private Map<String, List<String>> openUrl;

    /**
     * 跳过认证
     */
    public static String SKIP_AUTH = "SKIP_AUTH";
    /**
     * token
     */
    public static String TOKEN = "TOKEN";
    /**
     * 用户id
     */
    public static String USER_ID = "USER_ID";
    /**
     * 权限
     */
    public static String PERMISSION = "PERMISSION";
}



package io.github.maodua.wrench.auth.config;

import io.github.maodua.wrench.auth.interceptor.ContextHolderInterceptor;
import io.github.maodua.wrench.auth.interceptor.AuthTokenInterceptor;
import io.github.maodua.wrench.auth.interceptor.OpenUrlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableConfigurationProperties
@ComponentScan(basePackages = "io.github.maodua.wrench.auth")
@Configuration
public class AuthConfig implements WebMvcConfigurer {
    @Autowired
    private OpenUrlInterceptor openUrlInterceptor;
    @Autowired
    private AuthTokenInterceptor authTokenInterceptor;
    @Autowired
    private ContextHolderInterceptor contextHolderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(openUrlInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authTokenInterceptor).addPathPatterns("/**");
        registry.addInterceptor(contextHolderInterceptor).addPathPatterns("/**");
    }
}

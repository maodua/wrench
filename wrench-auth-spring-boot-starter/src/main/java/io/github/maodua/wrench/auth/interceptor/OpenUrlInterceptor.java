package io.github.maodua.wrench.auth.interceptor;


import io.github.maodua.wrench.auth.config.AuthConfigProperties;
import io.github.maodua.wrench.auth.exception.AuthException;
import io.github.maodua.wrench.auth.util.ContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class OpenUrlInterceptor implements AsyncHandlerInterceptor {
    @Autowired
    private AuthConfigProperties configProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求前执行逻辑

        // 请求路径
        String path = request.getRequestURI();
        // 请求方法
        String method = request.getMethod();

        // 1. 不需要的直接放行
        boolean isOpen = verifyOpenUrl(method, path);
        if (isOpen){
            // 跳过认证
            request.setAttribute(AuthConfigProperties.SKIP_AUTH, true);
            return true;
        }

        String authorization = request.getHeader("Authorization");
        // 2 判断是否有 token
        if (StringUtils.isBlank(authorization)){
            throw new AuthException("Auth 请求参数异常");
        }

        // 3 传递 token
        request.setAttribute(AuthConfigProperties.TOKEN, authorization);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在响应完成后执行逻辑
        ContextHolder.remove();
    }


    /**
     * 验证url是否开放
     * @param method 请求方式
     * @param url 请求url
     * @return 是否开放
     */
    public boolean verifyOpenUrl(String method, String url){
        // 当前请求所有开放的url
        List<String> openUrls = Optional.ofNullable(configProperties.getOpenUrl()).map(ou -> ou.get(method)).orElse(new ArrayList<>());

        long count = openUrls.stream().filter(u -> new AntPathMatcher().match(u, url)).count();
        return count > 0;
    }
}

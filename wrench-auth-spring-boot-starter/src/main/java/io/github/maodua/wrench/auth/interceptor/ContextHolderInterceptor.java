package io.github.maodua.wrench.auth.interceptor;

import io.github.maodua.wrench.auth.config.AuthConfigProperties;
import io.github.maodua.wrench.auth.util.ContextHolder;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

@Component
public class ContextHolderInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求前执行逻辑
        Boolean skip = Optional.ofNullable(request.getAttribute(AuthConfigProperties.SKIP_AUTH))
            .map(s -> Objects.toString(s, ""))
            .map(Boolean::valueOf)
            .orElse(false);
        if (BooleanUtils.isTrue(skip)){
            ContextHolder.set(ContextHolder.LOGIN, false);
            return true;
        }
        ContextHolder.set(ContextHolder.LOGIN, true);
        ContextHolder.set(ContextHolder.USER_ID, request.getAttribute(AuthConfigProperties.USER_ID));
        ContextHolder.set(ContextHolder.TOKEN, request.getAttribute(AuthConfigProperties.TOKEN));
        ContextHolder.set(ContextHolder.AUTH, request.getAttribute(AuthConfigProperties.PERMISSION));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在响应完成后执行逻辑
        ContextHolder.remove();
    }
}

package io.github.maodua.wrench.auth.aop;

import io.github.maodua.wrench.auth.annotation.Auth;
import io.github.maodua.wrench.auth.exception.AuthException;
import io.github.maodua.wrench.auth.util.ContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 权限插件
 */
@Component
@Aspect
public class AuthPlugin {
    @Around("@annotation(auth)")
    public Object aroundHandler(ProceedingJoinPoint point, Auth auth) throws Throwable{
        List<String> permission = ContextHolder.getAuth();
        if (!permission.contains(auth.value())) {
            throw new AuthException("权限不足，无法访问");
        }
        return point.proceed();
    }
}

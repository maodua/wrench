package io.github.maodua.wrench.auth.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.maodua.wrench.auth.config.AuthConfigProperties;
import io.github.maodua.wrench.auth.exception.AuthException;
import io.github.maodua.wrench.auth.util.ContextHolder;
import io.github.maodua.wrench.auth.util.TokenUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AuthTokenInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求前执行逻辑
        Boolean skip = Optional.ofNullable(request.getAttribute(AuthConfigProperties.SKIP_AUTH))
            .map(Object::toString)
            .map(Boolean::valueOf)
            .orElse(false);

        if (BooleanUtils.isTrue(skip)){
            return true;
        }

        try {
            String token = Objects.toString(request.getAttribute(AuthConfigProperties.TOKEN), "");
            // 排除自定前缀
            token = token.replace("Bearer ", "").trim();
            // Token解码
            DecodedJWT jwt = TokenUtil.getJWT(token);
            // 获取用户id
            String userId = TokenUtil.getUserId(jwt);
            // 获取权限列表
            List<String> authList = TokenUtil.getAuth(jwt);

            if (StringUtils.isBlank(userId)){
                throw new AuthException("请登录用户访问");
            }
            request.setAttribute(AuthConfigProperties.USER_ID, userId);
            request.setAttribute(AuthConfigProperties.PERMISSION, authList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException("请登录用户访问");
        }
        return true;
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在响应完成后执行逻辑
        ContextHolder.remove();
    }
}

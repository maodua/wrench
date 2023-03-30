package io.github.maodua.wrench.auth.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 获取当前线程变量中的 用户id、用户名称、Token等信息
 * 注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 *
 * @author ruoyi
 */
public class ContextHolder {
    /**
     * 用户id
     */
    public static final String USER_ID = "USER_ID";
    /**
     * 请求的 Token 的key
     */
    public static final String TOKEN = "TOKEN";
    /**
     * 是否登录key
     */
    public static final String LOGIN = "LOGIN";
    /**
     * 用户权限key
     */
    public static final String AUTH = "AUTH";
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取用户id
     */
    public static String getUserId() {
        if (ContextHolder.get(USER_ID) == null) {
            return null;
        }
        return ContextHolder.get(USER_ID).toString();
    }


    /**
     * 是否登录
     */
    public static boolean isLogin() {
        return Optional.ofNullable(ContextHolder.get(LOGIN))
            .map(Objects::toString)
            .map(Boolean::valueOf)
            .orElse(false);
    }

    /**
     * 用户权限
     */
    public static List<String> getAuth() {
        Object auth = ContextHolder.get(AUTH);
        if (auth == null) {
            return Collections.emptyList();
        }
        if (auth instanceof Collection){
            return (List<String>) auth;
        }else {
            return Collections.emptyList();
        }
    }


    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = getLocalMap();
        return map.get(key);
    }


    public static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<String, Object>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}

package com.github.maodua.wrench.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在非Spring注入的Bean中使用获取 Request
 */
public class Servlets {

    public static ServletRequestAttributes SERVLET_REQUEST = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    public static HttpServletRequest  getRequest(){
        return SERVLET_REQUEST.getRequest();
    }

    public static HttpServletResponse getResponse(){
        return SERVLET_REQUEST.getResponse();
    }
}

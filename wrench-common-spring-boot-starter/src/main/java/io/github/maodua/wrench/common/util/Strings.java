package io.github.maodua.wrench.common.util;

import org.springframework.util.StringUtils;

/**
 * String 工具类
 */
public class Strings {
    public static boolean isBlank(String s){
        return StringUtils.hasText(s);
    }
}

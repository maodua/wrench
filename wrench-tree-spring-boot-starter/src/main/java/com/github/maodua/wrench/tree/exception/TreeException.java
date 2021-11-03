package com.github.maodua.wrench.tree.exception;

/**
 * 用于必须抛出异常，又想给前端传递信息的情况<br>
 */
public class TreeException extends RuntimeException {

    public TreeException(String message) {
        super(message);
    }

    public TreeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeException(Throwable cause) {
        super(cause);
    }

    public TreeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

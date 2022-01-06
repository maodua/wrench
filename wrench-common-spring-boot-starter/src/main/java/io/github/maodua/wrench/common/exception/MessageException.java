package io.github.maodua.wrench.common.exception;

/**
 * 用于必须抛出异常，又想给前端传递信息的情况<br>
 */
public class MessageException extends RuntimeException {

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }

    public MessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

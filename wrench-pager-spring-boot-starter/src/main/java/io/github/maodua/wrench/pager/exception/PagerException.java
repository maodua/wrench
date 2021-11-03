package io.github.maodua.wrench.pager.exception;

/**
 * 分页过程中出现的异常
 */
public class PagerException extends RuntimeException {
    public PagerException() {}

    public PagerException(String message) {
        super(message);
    }

    public PagerException(Throwable cause) {
        super(cause);
    }
}

package io.github.maodua.wrench.common.vo.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 公共返回值
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 状态码，公共约定：
     * < 0: 错误
     * >= 0: 成功
     * -1 ~ -99: 公用错误码
     */
    private int code;
    /**
     * 错误信息(失败时有)
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success(T data, int code) {
        Result<T> result = new Result<>();
        result.setSuccess(true)
                .setCode(code)
                .setMessage("")
                .setData(data);
        return result;
    }

    public static <T> Result<T> success(T data) {
        return Result.success(data, ResultCode.SUCCESS.getCode());
    }

    public static <T> Result<T> success() {
        return Result.success(null);
    }

    public static <T> Result<T> fail(String message, int code) {
        Result<T> result = new Result<>();
        result.setSuccess(false)
                .setCode(code)
                .setMessage(message)
                .setData(null);
        return result;
    }

    public static <T> Result<T> fail(String message) {
        return Result.fail(message, ResultCode.FAIL.getCode());
    }

    public static <T> Result<T> fail() {
        return Result.fail("");
    }

    public static <T> Result<T> auto(boolean success, String failMessage) {
        return success ? Result.success() : Result.fail(failMessage);
    }

    public static <T> Result<T> auto(boolean success) {
        return Result.auto(success, "");
    }
}

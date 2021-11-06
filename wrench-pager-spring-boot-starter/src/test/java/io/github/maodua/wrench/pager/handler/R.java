package io.github.maodua.wrench.pager.handler;

import io.github.maodua.wrench.pager.handler.rcode.CommonReturnCode;
import io.github.maodua.wrench.pager.handler.rcode.IReturnCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应信息主体
 */
@ToString
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    public Integer getCode() {
        return this.code;
    }
    protected R<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    private String msg;
    public String getMsg() {
        return this.msg;
    }
    protected R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    private T data;
    public T getData() {
        return this.data;
    }
    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public R<T> setReturnCode(IReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
        return this;
    }

    /**
     * 快捷方法：接口执行成功，并返回默认结果
     *
     * @return
     */
    public static R successEmpty() {
        return R.builder()
                .code(CommonReturnCode.SUCCESS.getCode())
                .msg(CommonReturnCode.SUCCESS.getMsg())
                .build();
    }

    /**
     * 快捷方法：接口执行成功，自定义返回消息，返回默认结果
     *
     * @param msg
     * @return
     */
    public static R successEmpty(String msg) {
        return R.builder()
                .code(CommonReturnCode.SUCCESS.getCode())
                .msg(msg)
                .build();
    }

    /**
     * 快捷方法：接口执行成功，并指定返回数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> success(T data) {
        return R.<T>builder()
                .code(CommonReturnCode.SUCCESS.getCode())
                .msg(CommonReturnCode.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    /**
     * 快捷方法：接口执行成功，并指定返回数据及自定义返回消息
     *
     * @param data
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> R<T> success(T data, String msg) {
        return R.<T>builder()
                .code(CommonReturnCode.SUCCESS.getCode())
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 快捷方法：接口执行失败，返回相关错误码
     *
     * @param returnCode
     * @return
     */
    public static R fail(IReturnCode returnCode) {
        return R.builder()
                .code(returnCode.getCode())
                .msg(returnCode.getMsg())
                .build();
    }

    /**
     * 快捷方法：接口执行失败，返回相关错误码
     *
     * @param returnCode
     * @param data
     * @return
     */
    public static <T> R<T> fail(IReturnCode returnCode, T data) {
        return R.<T>builder()
                .code(returnCode.getCode())
                .msg(returnCode.getMsg())
                .data(data)
                .build();
    }
    public static <T> R<T> fail(IReturnCode returnCode, String error, T data) {
        return R.<T>builder()
                .code(returnCode.getCode())
                .msg(returnCode.getMsg() + " - " + error)
                .data(data)
                .build();
    }

    /**
     * 快捷方法：接口执行失败，返回自定义错误信息（返回码为99999）
     *
     * @param errorMsg
     * @return
     */
    public static R fail(String errorMsg) {
        return R.builder()
                .code(CommonReturnCode.UNKNOWN_ERROR.getCode())
                .msg(errorMsg)
                .build();
    }

    /**
     * 快捷方法：接口执行失败，返回自定义错误信息（返回码为99999）
     *
     * @param errorMsg
     * @param data
     * @return
     */
    public static <T> R<T> fail(String errorMsg, T data) {
        return R.<T>builder()
                .code(CommonReturnCode.UNKNOWN_ERROR.getCode())
                .msg(errorMsg)
                .data(data)
                .build();
    }

    /**
     * 快捷方法：接口执行失败，返回异常类的错误信息（返回码为15000）
     *
     * @param e
     * @return
     */
    public static R fail(Throwable e) {
        return R.builder()
                .code(CommonReturnCode.PROGRAM_ERROR.getCode())
                .msg(e.getLocalizedMessage())
                .build();
    }

    /**
     * 快捷方法：接口执行失败，返回异常类的错误信息（返回码为15000）
     *
     * @param e
     * @param data
     * @return
     */
    public static <T> R<T> fail(Throwable e, T data) {
        return R.<T>builder()
                .code(CommonReturnCode.PROGRAM_ERROR.getCode())
                .msg(e.getLocalizedMessage())
                .data(data)
                .build();
    }

    protected static <T> RBuilder<T> builder() {
        return new RBuilder();
    }

    protected static class RBuilder<T> {
        private int code;
        private String msg;
        private T data;


        RBuilder() {
        }

        public RBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public RBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public RBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public R<T> build() {
            return new R().setCode(this.code).setMsg(this.msg).setData(this.data);
        }

        @Override
        public String toString() {
            return "R.RBuilder(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ")";
        }
    }
}

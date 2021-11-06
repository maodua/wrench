package io.github.maodua.wrench.starter.handler.rcode;


/**
 * 通用接口返回码
 *
 * @author 赵梓彧 - kitchen_dev@163.com
 */
public enum CommonReturnCode implements IReturnCode {
    /**
     * 返回码规则、范围
     * code返回码使用5位数字，存储网关返回码（可以看做对错误码的分类或错误码的类型）
     * <p>
     * 0、10000-19999、99999为平台预留返回码，其它数值为业务系统自定义返回码
     */

    // 20000
    SUCCESS(0, "SUCCESS"),

    // 11000 - 签名验证失败
    GATEWAY_HEADER_PARAMS_ABSENT(10100,"缺少请求头参数"),
    GATEWAY_SIGN_PARAMS_ABSENT(10101, "缺少签名参数"),
    GATEWAY_SIGN_FORMAT_FAIL(10102, "签名参数格式错误"),
    GATEWAY_CHECK_SIGN_FAIL(10103, "签名验证错误"),
    GATEWAY_CHECK_TIMESTAMP_FAIL(10104, "请求超时"),
    GATEWAY_CHECK_TOKEN_FAIL(10105, "Token验证错误"),
    GATEWAY_SIGN_PUBLIC_KEY_ABSENT(10106, "未找到对应的公钥"),
    GATEWAY_SIGN_TYPE_FAIL(10107, "未找到符合条件的签名类型"),
    GATEWAY_TOKEN_ABSENT(10108, "Token不存在或已过期"),
    GATEWAY_REQUEST_URI_FORMAT(10109,"请求地址格式错误"),

    API_NOT_FOUND(14000, "请求的接口地址不存在"),
    UNSUPPORTED_METHOD_TYPE(14001, "不支持的请求方法类型（405）"),
    UNSUPPORTED_MEDIA_TYPE(14002, "不支持的请求媒体类型（415）"),

    REQ_PARAM_ERROR(14003, "请求的接口参数有误"),
    REQ_PARAM_FORMAT_ERROR(14004, "请求参数的格式有误"),

    REQ_JSON_FORMAT_ERROR(14005, "请求参数的JSON格式错误"),

    FUSING_TIMEOUT(14100, "请求超时熔断"),

    FUSING_ERROR(14199, "熔断器异常"),

    OTHER_HTTP_ERROR(14999, "未知的HTTP错误"),

    // 资源异常
    OBJ_ABSENT(12001, "操作的业务对象不存在"),
    SERVICE_ABSENT(12002, "服务暂不可用"),

    // 资源权限异常
    USER_NO_PERMISSION(12003, "用户无权限操作所请求资源"),

    // 50000 - 程序异常
    PROGRAM_ERROR(15000, "程序异常"),
    PROGRAM_FAILURE(15001, "程序原因，执行失败"),

    // 99999 - 未知异常
    UNKNOWN_ERROR(99999, "未知异常"),
    //vod或者oss生成token失败
    TOKEN_ERROR(13001,"生成token失败"),

    SIGN_RSA_NOT_PERMIT(16000,"请求不支持rsa验签");

    private Integer _code;
    private String _msg;

    CommonReturnCode(Integer code, String msg) {
        this._code = code;
        this._msg = msg;
    }

    @Override
    public Integer getCode() {
        return this._code;
    }

    @Override
    public String getMsg() {
        return this._msg;
    }

    @Override
    public String toString() {
        return this._code + "-" + this._msg;
    }
}

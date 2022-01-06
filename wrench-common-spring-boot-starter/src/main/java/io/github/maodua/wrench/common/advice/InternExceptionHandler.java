package io.github.maodua.wrench.common.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.github.maodua.wrench.common.exception.MessageException;
import io.github.maodua.wrench.common.vo.result.Result;
import io.github.maodua.wrench.common.vo.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.text.MessageFormat;

/**
 * 公共异常处理器，用于处理控制器抛出的异常<br/>
 * 为防止和其他项目中@ControllerAdvice起来冲突,默认不扫描本类 <br/>
 * 使用 @ComponentScan(basePackageClasses= InternExceptionHandler.class) 方式添加到容器中
 */
@ControllerAdvice
@Slf4j
public class InternExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Result<?>> handleException(Exception e) {
        // 输出错误日志
        log.error("", e);

        // 默认输出服务器内部错误
        Result<?> r = Result.fail("服务器内部错误", ResultCode.INTERNAL_SERVER_ERROR.getCode());

        // 调试模式下可以输出具体的错误信息
        if (e instanceof MethodArgumentNotValidException exception) {
            // 参数检查错误
            r.setMessage(exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        }else if (e instanceof NoHandlerFoundException){
            // 请求地址不存在
            r.setMessage("请求地址不存在");
        } else if (e instanceof ServletRequestBindingException){
            // 参数检查错误
            r.setMessage("参数检查错误");
        }else if (e instanceof IllegalArgumentException){
            // 非法参数异常
            r.setMessage("非法参数异常");
        }else if (e instanceof InvalidFormatException){
            r.setMessage("无效格式异常");
        }else if (e instanceof MessageException exception) {
            // 自定义的错误信息
            String message = exception.getMessage();
            r.setMessage(message);
        }else if (r.getMessage() == null) {
            r.setMessage("");
        }

        // 返回结果
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class })
    @ResponseBody
    public ResponseEntity<Result<?>> handleException(HttpRequestMethodNotSupportedException e) {
        log.error("请求方式不支持", e);
        Result<?> r = Result.fail(MessageFormat.format("不支持{0}请求",e.getMethod()), ResultCode.INTERNAL_SERVER_ERROR.getCode());
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
}

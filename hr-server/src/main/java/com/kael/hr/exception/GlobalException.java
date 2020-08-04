package com.kael.hr.exception;

import com.kael.hr.responst.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * 拦截自定义异常
     * @param e 自定义异常
     */
    @ExceptionHandler(value = HrException.class)
    public Result hrHandle(HrException e) {
        e.printStackTrace();
        log.error("{}",e.getMessage());
        return Result.failure(e.getStatue());
    }

    /**
     * 参数校验失败异常处理器
     * @param e 参数校验异常类
     * @return 校验失败的字段和信息，并设置http状态码
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result argumentNotValidHandle(MethodArgumentNotValidException e) {
        log.error("{}",e.getBindingResult().getFieldError().getDefaultMessage());
        e.printStackTrace();
        return Result.failure(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 无效参数异常处理器
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result messageNotReadableHandle(HttpMessageNotReadableException e) {
        log.error("{}",e.getMessage());
        e.printStackTrace();
        return Result.failure("无效参数");
    }


    /**
     * 其他未知异常处理器
     * @param e 异常
     * @return 信息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Result otherHandle(Exception e) {
        log.error("其他异常");
        e.printStackTrace();
        return Result.failure("未知异常");
    }
}

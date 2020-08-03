package com.kael.hr.exception;

import com.kael.hr.responst.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    /**
     * 拦截自定义异常
     * @param e 自定义异常
     */
    @ExceptionHandler(value = HrException.class)
    public Result handle(HrException e) {
        return Result.failure(e.getStatue());
    }
}

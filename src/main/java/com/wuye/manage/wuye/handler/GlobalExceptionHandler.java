package com.wuye.manage.wuye.handler;

import com.wuye.manage.wuye.dto.Response;
import com.wuye.manage.wuye.exception.YqException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(YqException.class)
    @ResponseBody
    Response errorResult(YqException e) {
        log.error(e.getErrorMsg());
        return new Response(e.getCode(), e.getErrorMsg());
    }

    /*@ExceptionHandler(Exception.class)
    @ResponseBody
    Response exceptionResult(Exception e) {
        log.error(e.getMessage());
        return new Response("300", e.getMessage());
    }*/
}

package com.ssm.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExecptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public String doException(UnauthorizedException e){
        System.out.println(e.getCause());


        return "unauthorized";
    }
}

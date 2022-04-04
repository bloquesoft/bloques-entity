/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:ControllerExceptionAdvice.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot.api;

import lombok.extern.slf4j.Slf4j;
import io.github.bloquesoft.entity.core.logic.error.LogicException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(LogicException.class)
    @ResponseBody
    public BaseApiResponse handleLogicException(LogicException ept) {
        return new BaseApiResponse(ept.getErrorCode(), ept.getErrorMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public BaseApiResponse handArgumentException(IllegalArgumentException ept) {

        if (ept.getMessage().startsWith("BEntity"))
        {
            return new BaseApiResponse("600", ept.getMessage());
        }
        return new BaseApiResponse("500", ept.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseApiResponse handOtherException(Exception ept) {
        String message = ept.getMessage();
        for (int i = 0; i < ept.getStackTrace().length; i++) {
            message += ept.getStackTrace()[i].toString() + "\n";
        }
        log.error(message);
        return new BaseApiResponse("500", message);
    }
}
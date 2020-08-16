package com.yamada.liveviewer.handler;

import com.yamada.liveviewer.exception.MyException;
import com.yamada.liveviewer.utils.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<Object> error(MyException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }
}

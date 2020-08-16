package com.yamada.liveviewer.exception;

import lombok.Data;

@Data
public class MyException extends RuntimeException {

    private Integer code;

    private String message;

    public MyException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

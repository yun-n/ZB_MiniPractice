package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
    private String code;
    private String message;
    private HttpStatus httpStatus;

    public AppException(ErrorCode errorCode, Object... args) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        if (args.length > 0) {
            this.message = String.format(errorCode.getMessage(), args);
        }
        this.httpStatus = errorCode.getHttpStatus();
    }
}

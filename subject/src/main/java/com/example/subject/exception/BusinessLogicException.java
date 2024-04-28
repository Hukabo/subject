package com.example.subject.exception;

import com.example.subject.exception.ExceptionCode;
import lombok.Getter;

@Getter
// Service layer에서 발생시키는 커스텀 예외
public class BusinessLogicException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
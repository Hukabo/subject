package com.example.subject.member.exception;

import com.example.subject.member.errorResponse.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler
    public ErrorResponse handleBusinessLogicException(BusinessLogicException e) {

        return ErrorResponse.of(e.getExceptionCode());
    }
}
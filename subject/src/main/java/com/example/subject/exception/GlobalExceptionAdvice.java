package com.example.subject.exception;

import com.example.subject.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handleBusinessLogicException(BusinessLogicException e) {

        return ErrorResponse.of(e.getExceptionCode());
    }

    @ExceptionHandler
    public ErrorResponse handleException(Exception e) {
        log.error("Exception: ", e);

        return ErrorResponse.of(e);
    }
}
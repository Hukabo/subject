package com.example.subject.exception;

import com.example.subject.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 예외 발생 시, 해당 예외를 캐치하여 ErrorResponse의 of 메서드를 통해 적절하게 응답
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    // BusinessLogicException 예외 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handleBusinessLogicException(BusinessLogicException e) {
        log.error("BusinessLogicException: ", e);

        return ErrorResponse.of(e.getExceptionCode());
    }

    // MethodArgumentNotValidException(validation에서 발생) 예외 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);

        return ErrorResponse.of(e.getBindingResult());
    }

    //그 외 모든 예외 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("Exception: ", e);

        return ErrorResponse.of(e);
    }
}
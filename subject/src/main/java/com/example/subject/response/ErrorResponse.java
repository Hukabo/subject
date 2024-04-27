package com.example.subject.response;

import com.example.subject.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Getter
public class ErrorResponse {

    private int status;
    private String message;
    private List<FieldError> fieldErrors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public static ErrorResponse of(ExceptionCode e) {

        return new ErrorResponse(e.getStatus(), e.getMessage());
    }

    public static ErrorResponse of(Exception e) {

        return new ErrorResponse(500, e.getMessage());
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult));
    }
}

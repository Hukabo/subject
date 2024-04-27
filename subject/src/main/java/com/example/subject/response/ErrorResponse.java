package com.example.subject.response;

import com.example.subject.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(ExceptionCode e) {

        return new ErrorResponse(e.getStatus(), e.getMessage());
    }

    public static ErrorResponse of(Exception e) {

        return new ErrorResponse(0,e.getMessage());
    }
}

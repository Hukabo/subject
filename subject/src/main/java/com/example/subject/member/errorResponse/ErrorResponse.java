package com.example.subject.member.errorResponse;

import com.example.subject.member.exception.BusinessLogicException;
import com.example.subject.member.exception.ExceptionCode;
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
}

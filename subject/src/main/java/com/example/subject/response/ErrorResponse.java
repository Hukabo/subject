package com.example.subject.response;

import com.example.subject.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

/**
 * @field status;
 * @field message;
 * @field fieldErrors;
 *
 * 예외 응답 객체
 */
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

    /**
     *
     * @param @ExceptionCode (in BusinessLogicException)
     * @return ErrorResponse
     */
    public static ErrorResponse of(ExceptionCode e) {

        return new ErrorResponse(e.getStatus(), e.getMessage());
    }

    /**
     *
     * @param @Exception
     * @return ErrorResponse
     */
    public static ErrorResponse of(Exception e) {

        return new ErrorResponse(500, e.getMessage());
    }

    /**
     *
     * @param @BindingResult (in MethodArgumentNotValidException)
     * @return ErrorResponse
     */
    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult));
    }
}

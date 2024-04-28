package com.example.subject.response;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Getter // Getter가 Error의 method를 구현
public class FieldError implements Error {
    private final String field;
    private final String rejectedValue;
    private final String reason;

    private FieldError(String field, String rejectedValue, String reason) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
    }

    /**
     *
     * @param bindingResult
     * @return List<FieldError>
     *
     * BindingResult에서 FieldError 추출 후 Error.from 호출
     */
    public static List<FieldError> of(BindingResult bindingResult) {
        final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

        return Error.from(fieldErrors,
                error -> new FieldError(
                        error.getField(),
                        error.getRejectedValue() == null ?
                                "" : error.getRejectedValue().toString(),
                        error.getDefaultMessage()
                ));
    }
}
package com.example.subject.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {


    MEMBER_ALREADY_EXIST(406, "이미 존재하는 회원입니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다.");

    private int status;
    private String message;
    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

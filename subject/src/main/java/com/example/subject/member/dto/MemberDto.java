package com.example.subject.member.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {

    /**
     * 회원 등록 Dto
     */
    @Getter
    public static class PostMemberDto {

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$",
                message = "8자리 이상 영어(대소문자), 숫자, 특수문자를 섞어주세요.")
        private String password;

        @NotBlank(message = "이름을 입력해주세요.")
        @Pattern(regexp = "^[a-zA-z가-힣]+(\\s?[a-zA-z가-힣])*$", message = "한글 혹은 영어로 입력해주세요.(특수 문자x)")
        private String name;

        @NotBlank(message = "닉네임 입력해주세요.")
        @Pattern(regexp = "^[a-zA-z가-힣]+$", message = "한글 혹은 영어로 입력해주세요.(특수 문자x,공백x)")
        private String nickName;

        @NotBlank(message = "번호를 입력해주세요.")
        @Pattern(regexp = "^01[0|1]\\d{3,4}\\d{4}", message = " '-' 없이 입력해주세요.")
        private String phoneNumber;
    }

    /**
     * 회원 수정 Dto
     */
    @Getter
    public static class PatchMemberDto {
        // email은 변경 불가능

        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$",
                message = "8자리 이상 영어(대소문자), 숫자, 특수문자를 섞어주세요.")
        private String password;

        @Pattern(regexp = "^[a-zA-z가-힣]+(\\s?[a-zA-z가-힣])*$", message = "한글 혹은 영어로 입력해주세요.(특수문자x)")
        private String name;

        @Pattern(regexp = "^[a-zA-z가-힣]+$", message = "한글 혹은 영어로 입력해주세요.(특수 문자x,공백x)")
        private String nickName;

        @Pattern(regexp = "^01[0|1]\\d{3,4}\\d{4}", message = " '-' 없이 입력해주세요.")
        private String PhoneNumber;
    }
}

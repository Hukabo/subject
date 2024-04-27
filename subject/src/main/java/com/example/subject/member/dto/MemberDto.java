package com.example.subject.member.dto;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MemberDto {

    @Data
    public static class PostMemberDto {

        private String email;

        private String password;

        private String name;

        private String nickName;

        private String phoneNumber;
    }

    @Data
    public static class PatchMemberDto {

        private String password;

        private String name;

        private String nickName;

        private String PhoneNumber;
    }
}

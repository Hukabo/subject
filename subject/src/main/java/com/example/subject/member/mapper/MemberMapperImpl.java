package com.example.subject.member.mapper;

import com.example.subject.member.dto.MemberDto;
import com.example.subject.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapperImpl implements MemberMapper {

    public Member postMemberDtoToMember(MemberDto.PostMemberDto postMemberDto) {

        if (postMemberDto == null) {
            return null;
        }

        return Member.builder()
                .email(postMemberDto.getEmail())
                .password(postMemberDto.getPassword())
                .name(postMemberDto.getName())
                .nickName(postMemberDto.getNickName())
                .phoneNumber(postMemberDto.getPhoneNumber())
                .build();
    }
}

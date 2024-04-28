package com.example.subject.member.mapper;

import com.example.subject.member.dto.MemberDto;
import com.example.subject.member.entity.Member;
import org.springframework.stereotype.Component;

import static com.example.subject.member.dto.MemberDto.*;

@Component
public class MemberMapperImpl implements MemberMapper {

    /**
     *
     * @param postMemberDto
     * @return Member
     *
     * postMemberDto -> Member
     */
    public Member postMemberDtoToMember(PostMemberDto postMemberDto) {

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

    /**
     *
     * @param patchMemberDto
     * @return Member
     *
     * patchMemberDto -> Member
     */
    public Member patchMemberToMember(PatchMemberDto patchMemberDto) {

        if (patchMemberDto == null) {
            return null;
        }

        return Member.builder()
                .password(patchMemberDto.getPassword())
                .name(patchMemberDto.getName())
                .nickName(patchMemberDto.getNickName())
                .phoneNumber(patchMemberDto.getPhoneNumber())
                .build();
    }
}

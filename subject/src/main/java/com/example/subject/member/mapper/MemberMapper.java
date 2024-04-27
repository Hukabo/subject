package com.example.subject.member.mapper;

import com.example.subject.member.dto.MemberDto;
import com.example.subject.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

public interface MemberMapper {
    Member postMemberDtoToMember(MemberDto.PostMemberDto postMemberDto);
}

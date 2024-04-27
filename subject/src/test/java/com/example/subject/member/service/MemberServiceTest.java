package com.example.subject.member.service;

import com.example.subject.member.entity.Member;
import com.example.subject.member.exception.BusinessLogicException;
import com.example.subject.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberService memberService;

    @Test
    void createMember() {
        //given
        Member member = getMember();

        //when
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        Member createdMember = memberService.createMember(member);

        //then
        assertThat(createdMember).isEqualTo(member);
    }

    @Test
    @DisplayName("회원이 이미 존재한다면, 예외 발생")
    void verifyExistMember_When_Member_Exists() {
        //given
        Member member = getMember();
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.of(member));

        //when, then
        assertThrows(BusinessLogicException.class, () -> {
            memberService.verifyExistMember(member.getEmail());
        });
    }

    @Test
    @DisplayName("회원이 존재하지 않는다면, 예외 미발생")
    void verifyExistMember_When_Member_Does_Not_Exists() {
        //given
        Member member = getMember();
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.empty());

        //when, then
        assertDoesNotThrow(() ->
            memberService.verifyExistMember(member.getEmail()));
    }

    private static Member getMember() {

        return Member.builder()
                .memberId(1L)
                .email("test@gmail.com")
                .name("testName")
                .password("1234")
                .nickName("testNickName")
                .phoneNumber("010-0000-0000")
                .build();
    }
}
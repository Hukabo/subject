package com.example.subject.member.service;

import com.example.subject.exception.BusinessLogicException;
import com.example.subject.member.entity.Member;
import com.example.subject.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
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
    @DisplayName("회원 가입 테스트")
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

    @Test
    @DisplayName("회원 목록 조회 테스트")
    void getMemberList() {
        //given
        List<Member> members = getMembers();
        PageImpl<Member> mockMemberPage = new PageImpl<>(members, PageRequest.of(0, 20).withSort(Sort.by(Sort.Direction.DESC, "memberId")), members.size());

        //when
        when(memberRepository.findAll(PageRequest.of(0,20).withSort(Sort.by(Sort.Direction.DESC, "memberId")))).thenReturn(mockMemberPage);
        Page<Member> memberPage = memberService.findMemberList(0, 20, "memberId", "desc");

        //then
        assertThat(memberPage.getTotalPages()).isEqualTo(mockMemberPage.getTotalPages());
        assertThat(memberPage.getSize()).isEqualTo(mockMemberPage.getSize());
        assertThat(memberPage.getNumberOfElements()).isEqualTo(mockMemberPage.getNumberOfElements());
        assertThat(memberPage.getSort()).isEqualTo(mockMemberPage.getSort());
        assertTrue(memberPage.stream().allMatch(member -> mockMemberPage.getContent().contains(member)));
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

    private static List<Member> getMembers() {

        List<Member> members = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Member member = getMember();
            member.setMemberId(i);
            members.add(member);
        }

        return members;
    }
}



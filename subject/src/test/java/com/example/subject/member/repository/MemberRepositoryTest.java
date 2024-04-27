package com.example.subject.member.repository;

import com.example.subject.member.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 저장 테스트")
    void saveMember() {
        //given
        Member member = getMember();

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    @DisplayName("이메일로 회원찾기 테스트")
    void findByEmail() {
        //given
        Member member = getMember();

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findByEmail(member.getEmail()).get();

        //then
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
    }

    private static Member getMember() {

        return Member.builder()
                .email("test@gmail.com")
                .name("testName")
                .password("1234")
                .nickName("testNickName")
                .phoneNumber("010-0000-0000")
                .build();
    }
}
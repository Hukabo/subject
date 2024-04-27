package com.example.subject.member.service;

import com.example.subject.member.entity.Member;
import com.example.subject.member.exception.BusinessLogicException;
import com.example.subject.member.exception.ExceptionCode;
import com.example.subject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(Member member) {

        verifyExistMember(member.getEmail()); // 회원 가입 시, 이미 존재하는 회원인지 검증

        return memberRepository.save(member);
    }

    public void verifyExistMember(String email) {
        // 만약 존재한다면 예외 발생
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXIST);
        }
    }
}

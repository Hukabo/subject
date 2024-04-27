package com.example.subject.member.service;

import com.example.subject.member.entity.Member;
import com.example.subject.exception.ExceptionCode;
import com.example.subject.exception.BusinessLogicException;
import com.example.subject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(Member member) {

        verifyExistMember(member.getEmail()); // 회원 가입 시, 이미 존재하는 회원인지 검증

        return memberRepository.save(member);
    }

    public Page<Member> findMemberList(int page, int pageSize, String field, String direction) {

        if (direction.equals("desc")) {
            return memberRepository.findAll(PageRequest.of(page, pageSize).withSort(Sort.by(Sort.Direction.DESC, field)));
        } else {
            return memberRepository.findAll(PageRequest.of(page, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
        }
    }

    public Member updateMember(Long memberId, Member member) {

        Member findMember = findVerifiedMember(memberId);

        Optional.ofNullable(member.getPassword()).ifPresent(findMember::setPassword);
        Optional.ofNullable(member.getNickName()).ifPresent(findMember::setNickName);
        Optional.ofNullable(member.getName()).ifPresent(findMember::setName);
        Optional.ofNullable(member.getPhoneNumber()).ifPresent(findMember::setPhoneNumber);

        return memberRepository.save(findMember);
    }

    private Member findVerifiedMember(long memberId) {

        return memberRepository.findById(memberId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public void verifyExistMember(String email) {
        // 만약 존재한다면 예외 발생
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXIST);
        }
    }
}

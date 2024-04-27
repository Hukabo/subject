package com.example.subject.member.controller;

import com.example.subject.member.entity.Member;
import com.example.subject.member.mapper.MemberMapper;
import com.example.subject.member.mapper.MemberMapperImpl;
import com.example.subject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.example.subject.member.dto.MemberDto.*;

@RestController
@Validated
@RequestMapping("/api/user")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapperImpl mapper;

    public MemberController(MemberService memberService, MemberMapperImpl mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping("/join")
    public ResponseEntity<?> postMember(@RequestBody PostMemberDto postMemberDto) {

        Member member = mapper.postMemberDtoToMember(postMemberDto);

        memberService.createMember(member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

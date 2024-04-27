package com.example.subject.member.controller;

import com.example.subject.member.entity.Member;
import com.example.subject.member.mapper.MemberMapper;
import com.example.subject.member.mapper.MemberMapperImpl;
import com.example.subject.member.service.MemberService;
import com.example.subject.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Optional;

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
    public ResponseEntity<?> postMember(@Valid @RequestBody PostMemberDto postMemberDto) {

        Member member = mapper.postMemberDtoToMember(postMemberDto);

        memberService.createMember(member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list")
    public PageResponse<Page<Member>> getMembers(@RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "field", defaultValue = "createdAt") Optional<String> field,
                                                 @RequestParam(value = "direction", defaultValue = "desc") Optional<String> direction) {
        Page<Member> memberList = memberService.findMemberList(page - 1, pageSize, field.orElse("createdAt"), direction.orElse("desc"));

        return new PageResponse<>(memberList.getSize(), memberList);
    }

    @PatchMapping("{member-id}")
    public ResponseEntity<?> patchMember(@Positive @PathVariable("member-id") Long memberId,
                                         @Valid @RequestBody PatchMemberDto patchMemberDto) {

        Member member = mapper.patchMemberToMember(patchMemberDto);

        Member updatedMember = memberService.updateMember(memberId, member);

        return ResponseEntity.ok(updatedMember);
    }
}

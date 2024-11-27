package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberRequestDto;
import com.example.demo.member.dto.MemberResponseDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.service.MemberService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/v1/users")
    public MemberResponseDto joinMember(@RequestBody MemberRequestDto memberRequestDto){
        Member member = new Member();

        member.setUsername(memberRequestDto.getUsername());
        member.setPassword(memberRequestDto.getPassword());
        member.setEmail(memberRequestDto.getEmail());

        return new MemberResponseDto(memberService.joinMember(member));
    }

    @GetMapping("/api/v1/users")
    public List<MemberResponseDto> getMemberList(){
        return memberService.getMemberList()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/users/{id}")
    public MemberResponseDto getMemberList(@Parameter(name = "id", description = "id", example = "1",in = ParameterIn.PATH) @PathVariable("id") Long id){
        return new MemberResponseDto(memberService.getMember(id));
    }
}

package com.example.demo.member.service;

import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.member.dto.MemberRequestDto;
import com.example.demo.member.dto.MemberResponseDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member joinMember(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            throw new AppException(ErrorCode.ALREADY_REGISTERED_EMAIL, memberRequestDto.getEmail());
        }

        Member member = new Member();
        member.setUsername(memberRequestDto.getUsername());
        member.setPassword(memberRequestDto.getPassword());
        member.setEmail(memberRequestDto.getEmail());

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    public List<MemberResponseDto> getMemberList() {
        return memberRepository.findAll()
                                .stream()
                                .map(MemberResponseDto::new)
                                .collect(Collectors.toList());
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ACCOUNT_ID, id));
    }
}

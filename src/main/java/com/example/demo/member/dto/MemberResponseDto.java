package com.example.demo.member.dto;

import com.example.demo.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.created_at = member.getCreated_at();
        this.updated_at = member.getUpdated_at();
    }

}

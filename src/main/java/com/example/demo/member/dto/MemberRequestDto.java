package com.example.demo.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

    private String username;
    private String password;
    private String email;
}
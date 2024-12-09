package com.example.demo.tag.dto;

import lombok.Getter;

@Getter
public class TagResultResponseDto {
    private boolean success;

    public TagResultResponseDto(boolean success) {
        this.success = success;
    }
}

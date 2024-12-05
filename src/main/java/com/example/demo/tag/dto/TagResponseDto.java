package com.example.demo.tag.dto;

import lombok.Getter;

@Getter
public class TagResponseDto {
    private boolean success;

    public TagResponseDto(boolean success) {
        this.success = success;
    }
}

package com.example.demo.category.dto;

import lombok.Getter;

@Getter
public class CategoryResultResponseDto {
    private boolean success;

    public CategoryResultResponseDto(boolean success) {
        this.success = success;
    }
}

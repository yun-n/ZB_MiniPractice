package com.example.demo.category.dto;

import com.example.demo.category.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private boolean success;

    public CategoryResponseDto(boolean success) {
        this.success = success;
    }
}

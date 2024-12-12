package com.example.demo.category.dto;

import com.example.demo.category.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryResponseDto {

    private Long id;

    private String code;

    private String name;

    public CategoryResponseDto(Category category){
        this.id = category.getId();
        this.code = category.getCode();
        this.name = category.getName();
    }
}

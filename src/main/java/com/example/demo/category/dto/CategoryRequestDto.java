package com.example.demo.category.dto;

import com.example.demo.book.entity.Book;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryRequestDto {
    @NotEmpty
    private String code;
    @NotEmpty
    private String name;
}

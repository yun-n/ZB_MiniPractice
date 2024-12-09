package com.example.demo.category.dto;

import com.example.demo.book.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryRequestDto {

    private String code;
    private String name;
}

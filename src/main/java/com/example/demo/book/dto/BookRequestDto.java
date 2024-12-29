package com.example.demo.book.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookRequestDto {
    @NotEmpty
    private String bookName;
    @NotEmpty
    private String bookWriter;
    @NotEmpty
    private String year;
    @NotEmpty
    private String ISBN;
    @NotEmpty
    private String categoryCode;
    @NotEmpty
    private String categoryName;

    private List<String> tagNames;
    private int bookQuantity;
}

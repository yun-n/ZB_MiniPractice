package com.example.demo.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookRequestDto {
    private String bookName;
    private String bookWriter;
    private String year;
    private String ISBN;
    private List<String> tagNames;
    private String categoryCode;
    private String categoryName;
    private int bookQuantity;
}

package com.example.demo.book.dto;

import com.example.demo.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BookResponseDto {

    private Long id;

    private String bookName;

    private String bookWriter;

    private String year;

    private String ISBN;

    private int bookQuantity;

    private String categoryCode;

    private String categoryName;

    private List<String> tags;

    public BookResponseDto(Book book){
        this.id = book.getId();
        this.bookName = book.getBookName();
        this.bookWriter = book.getBookWriter();
        this.year = book.getYear();
        this.ISBN = book.getISBN();
        this.bookQuantity = book.getBookQuantity();
        if(book.getCategory() != null){
            this.categoryCode = book.getCategory().getCode();
            this.categoryName = book.getCategory().getName();
        }

        this.tags = book.getTags().stream().map(tag ->tag.getName()).collect(Collectors.toList());
    }
}
